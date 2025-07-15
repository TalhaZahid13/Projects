<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        .container {
            width: 100%;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        label {
            font-weight: bold;
            color: #555;
        }
        input, textarea, button {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        textarea {
            resize: vertical;
        }
        button {
            background-color: #5cb85c;
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #4cae4c;
        }
    </style>
    <script>
        function handleSubmit(event) {
            // Prevent the default form submission behavior
            event.preventDefault();

            // Show an alert message
            alert("Feedback successful!");

            // Optionally, reset the form
            event.target.reset();
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Feedback Form</h1>
        <!-- Updated form with action to feedback servlet -->
        <form action="feedback" method="post" onsubmit="handleSubmit(event)">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" placeholder="Your name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Your email" required>

            <label for="message">Message:</label>
            <textarea id="message" name="message" placeholder="Write your feedback here..." rows="5" required></textarea>

            <button type="submit">Submit Feedback</button>
        </form>
    </div>
</body>
</html>