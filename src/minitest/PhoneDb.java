package minitest;



public class PhoneDb {

	public static void main(String[] args) {

	
		//생성자 만드는 순간 br에서 불러온 데이터가 pList에 담긴다.
		//ArrayList객체와 스캐너도 만들어짐.
		Phone phone = new Phone();
		
		
		boolean run = true;
		
		phone.showInfo();	//프로그램 시작
		
		while(run) {	//while문 시작
			
		int num = phone.showMenu();	//숫자를 입력할때마다 print출력하고 num 리턴
		
		switch(num) {
		
		case 1: phone.showList();	//1.리스트
		break;
		
		case 2: phone.addList();	//2.등록
		break;
	
		case 3:phone.delList();		//3.삭제
		break;
	
		case 4:phone.searchList();	//4.검색
		break;
	
		case 5: run = false;		//5.종료
		break;
		
		default : phone.noneNum();
			}	//switch문 끝
		
		}//while문 끝
			
	phone.escList();		
		
		
		
		
		
		
		
		
		
		
		
	}

	

}
