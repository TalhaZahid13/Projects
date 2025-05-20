#include<iostream>
#include<conio.h>
#include<fstream>
#include<chrono>
#include<ctime>
using namespace std; 
char player_1[25], player_2[25];
char N[3][3]={{'1', '2', '3'},{'4', '5', '6'},{'7', '8', '9'}};
int row, column, loop, draw=1, P1=0, P2=0,D=0, a=1;
char ST, turn='X';
void player() // To get players name.
{
	//cin.getline used to get whole line as input.
	cout<<"\n\tEnter Player_1 Name: "; 
	cin.getline(player_1, 25);
	cout<<"\tEnter Player_2 Name: ";
	cin.getline(player_2, 25);
}
void S_T() 
{
	correct:
	cout<<"\n\tIf you want to play a single match Press 'S' And for tournament press 'T': ";
	cin>>ST; 
	if(ST=='S' || ST=='s') 
	{
		loop=1;
	}	
	else if(ST=='T' || ST=='t')
	{
		start:
		cout<<"\n\tEnter how many times you want to play ->";
		cin>>loop;
		if(loop%2!=0)
		{
			//It is empty because if numer of games are odd the game will start in the main function.
		}
		else
		{
			system("cls");
			cout<<"\n  Please enter odd numbers to play tournament \n";
			goto start;
		}
	}
	else
	{
		system("cls");
		cout<<"\n  Invalid Choice Enter Again: ";
		goto correct;
	}
}
void F_H1() 
{

	int G=0;
	ofstream write;
	write.open("text.txt", ios::app);
	
	write<<"\n\nGame "<<++G<<endl;
	{
    auto start = chrono::system_clock::now();
    // Some computation here
    auto end = chrono::system_clock::now();
 
    chrono::duration<double> elapsed_seconds = end-start;
    time_t end_time = chrono::system_clock::to_time_t(end);
 
    write<<"\t"<<ctime(&end_time)<< endl;
}
	write<<"player 1 Name: "<<player_1<<endl;
	write<<"player 2 Name: "<<player_2<<endl;
	
	if(ST=='S' || ST=='s')
	{
		write<<"Single Match "<<endl;
	}
	if(ST=='T' || ST=='t')
	{
		write<<"Tournament "<<endl;
	}
	write<<"\n\t\t\t\tTic Tac Toe Game\n";
	write<<"\t\t\t\t     START\n";
	write<<"\t\t\t\t   Player 1[X]\n";
	write<<"\t\t\t\t   Player 2[O]\n";
	write.close();
}
void DATE()
{
    auto start = chrono::system_clock::now();
    auto end = chrono::system_clock::now();
    chrono::duration<double> elapsed_seconds = end-start;
    time_t end_time = chrono::system_clock::to_time_t(end);
 
    cout <<"\t"<<ctime(&end_time)<< endl;
}
void structure()
{
	cout<<"\n\t\t\t\tTic Tac Toe Game\n";
	cout<<"\t\t\t\t     START\n";
	cout<<"\t\t\t\t   Player 1[X]\n";
	cout<<"\t\t\t\t   Player 2[O]\n";
	cout<<"\n\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"\t"<<N[0][0]<<"\t|\t"<<N[0][1]<<"\t|\t"<<N[0][2]<<"\t\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"----------------|---------------|------------\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"\t"<<N[1][0]<<"\t|\t"<<N[1][1]<<"\t|\t"<<N[1][2]<<"\t\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"----------------|---------------|-------------\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"\t"<<N[2][0]<<"\t|\t"<<N[2][1]<<"\t|\t"<<N[2][2]<<"\t\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
	cout<<"\t\t"<<"\t\t|\t\t|\n";
}
void player_turn() 
{
	char choice;
	q:
	if(turn=='X') {
		cout<<"\n\t\tPlayer_1->"<<player_1<<"'s Turn: ";	}
	if(turn=='O') {
		cout<<"\n\t\tPlayer_2->"<<player_2<<"'s Turn: ";	}
	choice=getche();               
	
	if(choice>='1' && choice<='9')
	{
		if(choice=='1')
		{
			row=0; column=0;
		}
		else if(choice=='2')
		{
			row=0; column=1;
		}
		else if(choice=='3')
		{
			row=0; column=2;
		}
		else if(choice=='4')
		{
			row=1; column=0;
		}
		else if(choice=='5')
		{
			row=1; column=1;
		}
		else if(choice=='6')
		{
			row=1; column=2;
		}
		else if(choice=='7')
		{
			row=2; column=0;
		}
		else if(choice=='8')
		{	
			row=2; column=1;
		}
		else if(choice=='9')
		{
			row=2; column=2;
		}
	}
	else
	{
		cout<<"\nInvalid Choice Enter Again:";
		goto q;
	}
	if(turn=='X' && N[row][column]!='X' && N[row][column]!='O') 
	{
		N[row][column]='X';
		turn='O';
	}
	else if(turn=='O' && N[row][column]!='X' && N[row][column]!='O') 
	{
		N[row][column]='O';
		turn='X';
	}
	else 
	{
		cout<<"\n\tBox already filled ";
		player_turn();
	}
}
bool game()
{
	for(int i=0;i<3;i++)
	{
		if(N[i][0]==N[i][1] && N[i][0]==N[i][2] || N[0][i]==N[1][i] && N[0][i]==N[2][i]) 
		{
			draw=0;
			return 0;
		}
	}
	if(N[0][0]==N[1][1] && N[0][0]==N[2][2] || N[0][2]==N[1][1] && N[1][1]==N[2][0]) 
	{
		draw=0;
		return 0;
	}
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{	
			if(N[i][j]!='X' && N[i][j]!='O')
			{ 
	    		return 1;
    		}	
		} 
	}
	draw = 1;
	return 0;
}
void F_H2()
{
	ofstream write;
	write.open("text.txt", ios::app);
	{
    	auto start = chrono::system_clock::now();
    	auto end = chrono::system_clock::now();
 	   chrono::duration<double> elapsed_seconds = end-start;
 	   time_t end_time = chrono::system_clock::to_time_t(end);
    	write<<"\t"<<ctime(&end_time)<< endl;
	}
	if(loop>1)
		{
			write<<"\n\tRound "<<a;	
		}
	write<<"\n\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"\t"<<N[0][0]<<"\t|\t"<<N[0][1]<<"\t|\t"<<N[0][2]<<"\t\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"----------------|---------------|------------\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"\t"<<N[1][0]<<"\t|\t"<<N[1][1]<<"\t|\t"<<N[1][2]<<"\t\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"----------------|---------------|-------------\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"\t"<<N[2][0]<<"\t|\t"<<N[2][1]<<"\t|\t"<<N[2][2]<<"\t\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	write<<"\t\t"<<"\t\t|\t\t|\n";
	if (turn=='X' && draw==0)
	{
		write<<endl<<player_2<<"  Wins ";
	}
	else if(turn=='O'&& draw==0 )
	{
		write<<endl<<player_1<<" Wins ";
	}
	else
	{
		write<<endl<<"Game Draw!"; 
	}
	write.close();
}
void result() 
{
	if (turn=='X' && draw==0)
	{
		cout<<player_2<<"  Wins \n";
		P2++;
	}
	else if(turn=='O'&& draw==0 )
	{
		cout<<player_1<<" Wins \n";
		P1++;
	}
	else
	{
		cout<<"Game Draw!\n"; 
		D++;
	}
}
void W_L_D()
{
	cout<<"\nPlayer 1 "<<player_1<<" Wins = "<<P1<<endl;
	cout<<"\nPlayer 2 "<<player_2<<" Wins = "<<P2<<endl;
	cout<<"\nDraws = "<<D<<endl;
}
void FH3() 
{
	ofstream write;
	write.open("text.txt", ios::app);
	write<<"\nPlayer 1 "<<player_1<<" Wins = "<<P1<<endl<<endl;
	write<<"\nPlayer 2 "<<player_2<<" Wins  = "<<P2<<endl<<endl;
	write<<"\nDraws = "<<D<<endl<<endl;
	write.close();
}
void view_history() 
{
  string line;
  ifstream history_file("text.txt");
  if (history_file.is_open()) 
  {
    while (getline(history_file, line)) 
	{
      cout << line << endl;
    }
    history_file.close();
  } 
  else 
  {
    cout << "Unable to open file";
  }
}
int main()
{
	
	player();
	S_T();
	F_H1(); 
	system("cls");
	for(a=1;a<=loop;a++) 
	{
		DATE();
		while(game()) 
		{
			
			if(loop>1)
			{
			 cout<<"\n\tRound "<<a;	
			}	
			structure();
			player_turn();
			system("cls");
		}
		F_H2();
		structure();
		result();
		char y='1';
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				N[i][j]=y;
				++y;
			}
		}
		system("pause");
		system("cls");
		W_L_D();
		system("pause");
		system("cls");
		FH3();
	}
	view_history();
}