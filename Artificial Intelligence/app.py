# app.py
from flask import Flask, request, render_template
import pandas as pd
from sklearn.linear_model import LogisticRegression
from sklearn.feature_extraction.text import CountVectorizer
import joblib
import io
import contextlib
import subprocess
import uuid
import os

# Load and prepare the dataset
df = pd.read_csv("ai_code_efficiency_dataset.csv")
vectorizer = CountVectorizer()
X = vectorizer.fit_transform(df['code'])
y = (df['efficiency_score'] > 65).astype(int)

# Train and save model
model = LogisticRegression()
model.fit(X, y)
joblib.dump(model, "model.pkl")
joblib.dump(vectorizer, "vectorizer.pkl")

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def index():
    result = None
    output = ""
    if request.method == "POST":
        code_input = request.form['code']
        lang = request.form['language']

        model = joblib.load("model.pkl")
        vectorizer = joblib.load("vectorizer.pkl")

        X_input = vectorizer.transform([code_input])
        prediction = model.predict(X_input)[0]
        confidence = model.predict_proba(X_input)[0][prediction]

        try:
            if lang == "Python":
                f = io.StringIO()
                with contextlib.redirect_stdout(f):
                    exec(code_input, {})
                output = f.getvalue()

            elif lang == "JavaScript":
                filename = f"temp_{uuid.uuid4().hex}.js"
                with open(filename, "w") as f:
                    f.write(code_input)
                result_sub = subprocess.run(["node", filename], capture_output=True, text=True, timeout=5, shell=True)
                output = result_sub.stdout + result_sub.stderr
                os.remove(filename)

            elif lang == "Java":
                file_id = uuid.uuid4().hex
                class_name = f"Main{file_id}"
                java_file = f"{class_name}.java"
                
                # Replace the class name in the submitted code
                if "public class Main" in code_input:
                    code_input = code_input.replace("public class Main", f"public class {class_name}")
                elif "class Main" in code_input:
                    code_input = code_input.replace("class Main", f"class {class_name}")
                
                with open(java_file, "w") as f:
                    f.write(code_input)
                
                # Compile Java code
                compile_result = subprocess.run(["javac", java_file], capture_output=True, text=True, shell=True)
                
                if compile_result.returncode == 0:
                    # Run the compiled Java class
                    run_result = subprocess.run(["java", class_name], capture_output=True, text=True, timeout=5, shell=True)
                    output = run_result.stdout + run_result.stderr
                    os.remove(f"{class_name}.class")
                else:
                    output = compile_result.stderr
                
                os.remove(java_file)


            elif lang == "Dart":
                filename = f"temp_{uuid.uuid4().hex}.dart"
                with open(filename, "w") as f:
                    f.write(code_input)
                result_sub = subprocess.run(["dart", filename], capture_output=True, text=True, timeout=5, shell=True)
                output = result_sub.stdout + result_sub.stderr
                os.remove(filename)
            else:
                output = "Language execution not supported."

        except Exception as e:
            output = f"Error while executing code: {e}"

        result = {
            "language": lang,
            "prediction": "Efficient" if prediction else "Inefficient",
            "confidence": round(confidence * 100, 2),
            "output": output
        }

    return render_template("index.html", result=result)

if __name__ == "__main__":
    app.run(debug=True)
