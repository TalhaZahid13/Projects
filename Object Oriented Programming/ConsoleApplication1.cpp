// 46287 MUHAMMAD TALHA
//
#include <fstream>
#include <string>
#include <iomanip>
#include <iostream>
#include <cstdlib>
using namespace std;
class login
{
	string admin;
public:
	int adlogin()
	{
		cout << "\t\t\t\t\t\t";
		cout << "ENTER THE ADMIN CODE : " << endl;
		cout << "\t\t\t\t\t\t";
		cin >> admin;
		if (admin == "admin")
		{
			int p = getadpassword();
			if (p == 1)
			{
				cout << "\t\t\t\t\t\t";
				cout << "--LOGIN SUCCESSFULLY--" << endl;
				return 1;
			}
		}
		else 
		{
			cout << "\t\t\t\t\t\t";
			cout << "USER NAME NOT FOUND :)" << endl;
			adlogin();
			cout << "\t\t\t\t\t\t";
			cout << "LOGIN FAILED :)" << endl;
			return 0;
		}
	}
	int getadpassword();
};
int login::getadpassword()
{
	string pw;
	cout << "\t\t\t\t\t\t";
	cout << "ENTER THE PASSWORD : " << endl;
	cin >> pw;
	if (pw == "talhazahid")
	{
		cout << "\t\t\t\t\t\t";
		cout << "WELCOME!!" << endl;
		return 1;
	}
	else 
	{
		cout << "\t\t\t\t\t\t";
		cout << "ENTERED PASSWORD IS INCORRECT :) " << endl;
		getadpassword();
		return 0;
	}
}
void allotment_gold(int gc, int sc)
{
	if (gc != 0)
	{
		cout << gc << endl;
		cout << "\t\t\t\t\t\t";
		cout << "TRAINER ALLOTED\n";
	}
	else
	{
		cout << "\t\t\t\t\t\t";
		cout << "NO TRAINER AVAILABLE\n";
	}
}
void allotment_silver(int gc, int sc)
{
	if (sc != 0)
	{
		cout << "\t\t\t\t\t\t";
		cout << sc << endl;
		cout << "TRAINER ALLOTED\n";
	}
	else
	{
		cout << "\t\t\t\t\t\t";
		cout << "\nNO TRAINER AVAILABLE\n";
		if (gc != 0)
		{
			cout << "\t\t\t\t\t\t";
			cout << "ENTER ANOTHER CLASS : \n";
			allotment_gold(gc, sc);
		}
	}
}
int trainee_allotment(char* c)
{
	char class_type[10];
	int i = 0, gold_class = 3, silver_class = 5;
	strcpy(class_type, c);
	if ((gold_class != 0) || (silver_class) != 0)
	{
		if (strcmp(class_type, "gold") == 0)
		{
			gold_class--;
			allotment_gold(gold_class, silver_class);
		}
		else if (strcmp(class_type, "silver") == 0)
		{
			silver_class--;
			allotment_silver(gold_class, silver_class);
		}
	}
	else
	{
		cout << "\t\t\t\t\t\t";
		cout << "\n\n\n\n--SORRY, NO TRAINER AVAILABE--\n";
		return 1;
	}
}
class member : public login
{
	int member_number = 0 , j = 0 ;
	char mem_name[50], classs[50], timings[50];
	float fee = 2000.0 ;
	long int contact = 321 ;
public:
	int time_slots()
	{
		int k;
		cout << "\t\t\t\t\t\t";
		cout << "PLEASE SLETECT YOUR PREFERED TIMINGS :\n\t\t\t\t\t\tPRESS 1 FOR MORINING : 6-7\n\t\t\t\t\t\tPRESS 2 FOR MORNING : 7-8\n\t\t\t\t\t\tPRESS 3 FOR MORNING : 8-9\n";
		cout << "\t\t\t\t\t\tPRESS 4 FOR EVENIG : 4-5\n\t\t\t\t\t\tPRESS 5 FOR EVENING : 5-6\n\t\t\t\t\t\tPRESS 6 FOR EVENING : 6-7\n";
		cout << "\t\t\t\t\t\t";
		cin >> k;
		switch (k)
		{
			cout << "\t\t\t\t\t\t";
			case 1:strcpy(timings, "6AM-7AM"); break;
			cout << "\t\t\t\t\t\t";
			case 2:strcpy(timings, "7AM-8AM"); break;
			cout << "\t\t\t\t\t\t";
			case 3:strcpy(timings, "8AM-9AM"); break;
			cout << "\t\t\t\t\t\t";
			case 4:strcpy(timings, "4PM-5PM"); break;
			cout << "\t\t\t\t\t\t";
			case 5:strcpy(timings, "5PM-6PM"); break;
			cout << "\t\t\t\t\t\t";
			case 6:strcpy(timings, "6PM-7PM"); break;
		}
		return k;
	}
	void create_mem()
	{
		cout << "\t\t\t\t\t\t";
		int k, l, j;
		cout << endl << "\t\t\t\t\t\tPLEASE ENTER THE MEMBER NUMBER : ";
		cin >> member_number;
		cout << endl;
		cout << "\t\t\t\t\t\t";
		cout << "PLEASE ENTER THE NAME OF THE MEMBER : ";
		cin >> mem_name;
		cin.getline(mem_name, 50);
		cout << "\t\t\t\t\t\t";
		cout << endl;
		cout << "PLEASE ENTER THE CONTACT NUMBER : ";
		cin >> contact;
		cout << "\t\t\t\t\t\t";
		cout << "(1) GOLD CLASS\n\t\t\t\t\t\t(2) SILVER CLASS\n";
		cout << "\t\t\t\t\t\tENTER THE CHOICE : \n";
		cout << "\t\t\t\t\t\t";
		cin >> k;
		switch (k)
		{
		cout << "\t\t\t\t\t\t";
		case 1: 
		{
			strcpy(classs, "gold");
			fee = 2500;
			cout << "\t\t\t\t\t\t";
			cout << "YOUR MONTHLY FEE WOULD BE : " << fee << endl;
			l = trainee_allotment(classs);
		}
			  break;
		case 2: 
		{
			cout << "\t\t\t\t\t\t";
			strcpy(classs, "silver");
			fee = 2000;
			cout << "\t\t\t\t\t\t";
			cout << "YOUR MONTHLY FEE WOULD BE : " << fee << endl;
			l = trainee_allotment(classs);
		}
			  break;
		}
		time_slots();
	}
	void show_mem()
	{
		cout << endl << "\t\t\t\t\t\tMEMBER CODE : " << member_number;
		cout << endl << "\t\t\t\t\t\tNAME : "		 << mem_name;
		cout << endl << "\t\t\t\t\t\tCATEGORY : "	 << classs;
		cout << endl << "\t\t\t\t\t\tFEE : "		 << fee;
		cout << endl << "\t\t\t\t\t\tCONTACT : "	 << contact;
		cout << endl << "\t\t\t\t\t\tTIMINGS : "	 << timings << endl;
	}
	int getmem()
	{
		cout << "\t\t\t\t\t\t";
		return member_number;
	}
	float getfee()
	{
		cout << "\t\t\t\t\t\t";
		return fee;
	}
	char* getName()
	{
		cout << "\t\t\t\t\t\t";
		return mem_name;
	}
	float getcontact()
	{
		cout << "\t\t\t\t\t\t";
		return contact;
	}
};
fstream fp;
member m1;
void save_member()
{
	fp.open("gymdata.txt", ios::out | ios::app);
	m1.create_mem();
	fp.write((char*)&m1, sizeof(m1));
	fp.close();
	system("cls");
	cout << endl << endl << "\t\t\t\t\t\tTHE MEMBER HAS BEEN SUCCESFULLY ADDED...";
//	getchar();
}
void show_all()
{
	system("cls");
	cout << endl << "\t\t\t\t\t\tRECORDS...";
	fp.open("gymdata.txt", ios::in);
	while (fp.read((char*)&m1, sizeof(m1)))
	{
		m1.show_mem();
//		getchar();
	}
	fp.close();
}
void display_record(int num)
{
	bool found = false;
	fp.open("gymdata.txt", ios::in);
	while (fp.read((char*)&m1, sizeof(m1)))
	{
		if (m1.getmem() == num)
		{
			system("cls");
			m1.show_mem();
			found = true;
		}
	}
	fp.close();
	if (found == true)
		cout << "\n\n\t\t\t\t\t\tSORRY, NO RECORD FOUND";
//	getchar();
}
void edit_member()
{
	int num;
	bool found = false;
	system("cls");
	cout << endl << endl << "\t\t\t\t\t\tPLEASE ENTER THE MEMBER NUMBER : ";
	cin >> num;
	fp.open("gymdata.txt", ios::in | ios::out);
	while (fp.read((char*)&m1, sizeof(m1)) && found == false)
	{
		if (m1.getmem() == num)
		{
			m1.show_mem();
			cout << "\n\t\t\t\t\t\tPLEASE ENTER THE NEW DETAILS OF THE MEMBER : " << endl;
			m1.create_mem();
			int pos = 1 * sizeof(m1);
			fp.seekp(pos, ios::cur);
			fp.write((char*)&m1, sizeof(m1));
			cout << endl << endl << "\t\t\t\t\t\t****RECORD SUCCESSFULLY UPDATED****";
			found = true;
		}
	}
	fp.close();
	if (found == false)
		cout << endl << endl << "\t\t\t\t\t\t****RECORD NOT FOUND****";
//	getchar();
}
void delete_member()
{
	int num;
	system("cls");
	cout << endl << endl << "\t\t\t\t\t\tPLEASE ENTER THE MEMBER NUMBER : ";
	cout << "\t\t\t\t\t\t";
	cin >> num;
	fp.open("gymdata.txt", ios::in | ios::out);
	fstream fp2;
	fp2.open("Temp2.txt", ios::out);
	fp.seekg(0, ios::beg);
	while (fp.read((char*)&m1, sizeof(m1)))
	{
		if (m1.getmem() != num)
		{
			fp2.write((char*)&m1, sizeof(m1));
		}
	}
	fp2.close();
	fp.close();
	remove("gymdata.txt");
	cout << endl << endl << "\t\t\t\t\t\tRECORD DELETED...";
//	getchar();
}
void fnmanage()
{
	for (;;)
	{
		system("cls");
		int option;
		cout << "\t\t\t\t\t\t***********************************************";
		cout << "\n\t\t\t\t\t\tPRESS 1 TO CREATE MEMBER";
		cout << "\n\t\t\t\t\t\tPRESS 2 TO DISPLAY ALL RECORDS";
		cout << "\n\t\t\t\t\t\tPRESS 3 to SEARCH FOR A PARTICULAR RECORD ";
		cout << "\n\t\t\t\t\t\tPRESS 4 to EDIT MEMBER DETAILS";
		cout << "\n\t\t\t\t\t\tPRESS 5 to DELETE MEMBER";
		cout << "\n\t\t\t\t\t\tPRESS 6 to GO BACK TO MAIN MENU";
		cout << "\n\t\t\t\t\t\t**********************************************";
		cout << "\n\n\t\t\t\t\t\tOPTION: ";
		cout << "\t\t\t\t\t\t";
		cin >> option;
		switch (option)
		{
		case 1: system("cls");
			save_member();
			break;

		case 2: show_all();
			break;

		case 3:
			int num;
			system("cls");
			cout << "\n\n\t\t\t\t\t\tPLEASE ENTER THE MEMBER NUMBER : ";
			cin >> num;
			display_record(num);
			break;

		case 4: edit_member();
			break;

		case 5: delete_member();
			break;

		case 6: system("cls");
			break;

		default:fnmanage();
		}
	}
}
void fnuser()
{
	for (;;) 
	{
		int m;
		cout << "\n\t\t\t\t\t\t1.JOIN GYM\n\t\t\t\t\t\t2.QUIT GYM\n\t\t\t\t\t\t3.EDIT YOUR PROFILE\n";
		cout << endl << "\t\t\t\t\t\tPLEASE, ENTER YOUR CHOICE : " << endl;
		cout << "\t\t\t\t\t\t";
		cin >> m;
		switch (m)
		{
		case 1:system("cls");
			save_member();
			break;
		case 2:delete_member();
			break;
		case 3:edit_member();
			break;
		}
	}
}
int main()
{
	int i, k;
	string name, code;
	cout << "\n\n\n";
	cout << "\t\t\t\t----------------------------------------------------------------------" << endl;
	cout << "\t\t\t\t|                                                                    |" << endl;
	cout << "\t\t\t\t|                   WELCOME TO THROWBACK FITNESS                     |" << endl;
	cout << "\t\t\t\t|                                                                    |" << endl;
	cout << "\t\t\t\t----------------------------------------------------------------------" << endl;
	cout << endl << "\t\t\t\t\t\t--PLESE SELECT YOUR MODE--" << endl << "\t\t\t\t\t\tPRESS (1) IF YOU ARE A GYM USER." << endl 
		 << "\t\t\t\t\t\tPRESS (2) IF YOU ARE A GYM ADMIN." << endl;
	cout << "\t\t\t\t\t\t";
	cin >> i;
	if (i == 1) 
	{
		cout << endl << "\t\t\t\t\t\t--YOU ARE CURRENLTY IN USER MODE--" << endl;
		cout << "\t\t\t\t\t\tGYM TIMMINGS ARE FROM 6 AM TO 7 PM";
		fnuser();
	}
	if (i == 2) 
	{
		cout << endl << "\t\t\t\t\t\t--YOU ARE CURRENLTY IN ADMIN MODE--" << endl;
		login ad;
		k = ad.adlogin();
		if (k == 1)
		{
			fnmanage();
		}
		else
		{
			cout << "\t\t\t\t\t\tSORRY, YOU CANNOT ACCESS MANAGER CONTROLS :)";
		}
	}
	return 0;
}
