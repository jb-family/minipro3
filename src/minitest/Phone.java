package minitest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phone {
	
	//필드
	private List<Person> pList;	//pList가 인터페이스 List에서 Person만 접근가능하도록 함
	private Scanner sc;			// 검색기능 
	
	
	//생성자
	public Phone() {
		pList = new ArrayList<Person>();	// 생성자 만들면서 객체 생성.
		sc = new Scanner(System.in);	// 스캐너 설정
		
		loadList();	//생성자 만드는 순간 loadList 출력.
	}
	
	
	//메소드 - 일반
	public void showInfo() {	//프로그램 출력양식
		System.out.println("***************************************");
		System.out.println("*        전화번호 관리 프로그램         *");
		System.out.println("***************************************");
	}
	
	public int showMenu() {	//메뉴출력양식
		System.out.println("");
		System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
		System.out.println("----------------------------------------");
		System.out.print(">메뉴번호 :");
		int num = sc.nextInt();
		return num;
	}
	
	
	public void showList() {	// 1.리스트 메소드
		System.out.println("<1.리스트>");
		printList();	//리스트 불러오기.
	}
	
	public void addList() {	// 2. 등록 메소드
		System.out.println("<2.등록>");
		System.out.print(">이름 :");
		String name = sc.next();
		System.out.print(">휴대전화 :");
		String hp = sc.next();
		System.out.print(">회사전화 :");
		String company = sc.next();
		pList.add(new Person(name, hp, company));	// 입력한 값들을 pList에 저장.
		saveList();	// 등록 후 새로 저장한다.
		System.out.println("[등록되었습니다.]");
	}
	
	public void delList() {	//3. 삭제 메소드
		System.out.println("<3.삭제>");
		System.out.print(">번호 :");
		int num = sc.nextInt();
		pList.remove(num - 1);	//pList는 index0부터 시작이기 떄문에 입력 값 -1을 해주어야 한다.
		saveList();	// 삭제 후 새로 저장한다.
		System.out.println("[삭제되었습니다.]");
	}
	
	public void searchList() {	//4. 검색 메소드
		System.out.println("<4.검색>");
		System.out.print(">이름 :");
		String search = sc.next();
		printList(search);
	}
	
	public void escList() {	//5. 종료 메소드
		System.out.println("<5.종료>");
		System.out.println("***************************************");
		System.out.println("*               감사합니다             *");
		System.out.println("***************************************");
		sc.close();		// 종료되면서 스캐너도 끝
	}
	
	public void noneNum() {
		System.out.println("[다시 입력해주세요.]");
	}
	
	
	
	private void saveList() {	//저장하는 메소드
		try {
			Writer wt = new FileWriter("./PhoneDB.txt"); // 파일을 새로 저장한다.
			BufferedWriter bw = new BufferedWriter(wt);
			
			for(int i = 0; i < pList.size(); i++) {
				
				bw.write(pList.get(i).getName() + "," + pList.get(i).getHp() + "," + pList.get(i).getCompany());
				bw.newLine();//데이터 받고 한줄띄우고 반복
				bw.flush();	//데이터가 용량보다 덜 찼을 때 보내주는 메소드
			}
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void printList() {
		printList(""); // key 값을 빈값으로 설정하여 List 모두 출력.
	}	//메소드 오버로딩
	
	
	private void printList(String key) {	//List 출력하는 메소드
		for(int i = 0; i < pList.size(); i++) {	//pList 담겨있는 모든 pList 출력
			if(pList.get(i).getName().contains(key)) {//key 값이 들어가는 문자가 있다면 들어간 문자 모두 출력. 
				System.out.println(i + 1 + "  " + pList.get(i).getName() + "  " + pList.get(i).getHp() + "  " + pList.get(i).getCompany());
			}
			
		}
	}
	
	
	
	private void loadList() {
		try {
			Reader rd = new FileReader("./PhoneDB.txt");//파일을 읽어온다.
			BufferedReader br = new BufferedReader(rd);
			String name;
			String hp;
			String company;
		
		while(true) {
			String read = br.readLine();//무한으로 br에 있는 데이터를 불러온다.
			if(read == null) {//불러오는 데이터가 없다면 break;
				break;	
			}
			
			String[] sArray = read.split(",");	//불러온 데이터를 , 기준으로 sArray 배열에 넣음.
			
			name = sArray[0];
			hp = sArray[1];
			company = sArray[2];
			
			pList.add(new Person(name, hp, company));	//pList에 person 생성자를 담는다.
			
		}//while문 끝
		
		
		 br.close();
		 
	}//try 끝 
	
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
}
	
	
	
	
	
