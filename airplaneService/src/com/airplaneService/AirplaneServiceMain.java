package com.airplaneService;

import java.sql.SQLException;
import java.util.Scanner;

import com.airplaneService.controller.BookingRegisterManager;
import com.airplaneService.controller.CountryRegisterManager;
import com.airplaneService.controller.CustomerRegisterManager;
import com.airplaneService.controller.FlightRegisterManager;
import com.airplaneService.controller.PlaneRegisterManager;
import com.airplaneService.controller.SeatsRegisterManager;
import com.airplaneService.model.BookingVO;
import com.airplaneService.view.CUSTOMER_LOGIN_CHOICE;
import com.airplaneService.view.FLIGHT_SEARCH_MENU_CHOICE;
import com.airplaneService.view.MANAGER_LOGIN_CHOICE;
import com.airplaneService.view.MANAGE_CUSTOMER_MENU_CHOICE;
import com.airplaneService.view.MANAGE_SUB_MENU_CHOICE;
import com.airplaneService.view.MENU_CHOICE;
import com.airplaneService.view.MYBOOKING__MENU_CHOICE;
import com.airplaneService.view.MYPAGE_MENU_CHOICE;
import com.airplaneService.view.MenuViewer;

public class AirplaneServiceMain {
	public static Scanner sc = new Scanner(System.in);
	// 메인
	public static void main(String[] args) throws SQLException {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.mainMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MENU_CHOICE.LOGIN:
				int i =Integer.parseInt(sc.nextLine()); 
				if(i==1) {//로그인 기능 제작, 로그인후 customerVO 반환받아서 관리자,일반고객 구분
				exitFlag=CustomerLoginMenu();//일반고객 로그인메뉴
				}else {
				exitFlag=managerLoginMenu();//관리자 로그인매뉴
				}
				break;
			case MENU_CHOICE.PRINTFLIGHT:
				flightSearchMenu(); // 운항정보 출력후, 해당 운항정보의남은 좌석 확인 또는 나가기
				break;
			case MENU_CHOICE.REGISTER:
				registerMenu(); //회원가입, 
				break;
			case MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}

	}
	//회원가입 메뉴
	private static void registerMenu() {
		
	}
	
	private static boolean managerLoginMenu() {
		int no = 0;
		while (true) {
			MenuViewer.managerLoginMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MANAGER_LOGIN_CHOICE.CUSTOMER:
				manageCustomerMenu();
				break;
			case MANAGER_LOGIN_CHOICE.PLANE:
				managePlaneMenu();
				break;
			case MANAGER_LOGIN_CHOICE.COUNTRY:
				manageCountryMenu();
				break;
			case MANAGER_LOGIN_CHOICE.FLIGHT:
				manageFlightMenu();
				break;
			case MANAGER_LOGIN_CHOICE.BOOKING:
				mangeBookingMenu();
				break;
			case MANAGER_LOGIN_CHOICE.LOGOUT:
				return false;
			case MANAGER_LOGIN_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				return true;
			}
		}
		
	}

	//관리자모드 예약관리
	private static void mangeBookingMenu() {
		int no = 0;
		boolean exitFlag = false;
		BookingRegisterManager brm = new BookingRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageBookingMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					brm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					brm.insertManager();;
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					brm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					brm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					brm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
		
	}
	
	//관리자모드 항공편 관리
	private static void manageFlightMenu() {
		int no = 0;
		boolean exitFlag = false;
		FlightRegisterManager frm = new FlightRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageFlightMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					frm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					frm.insertManager();;
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					frm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					frm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					frm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
		
	}

	//관리자모드 나라 관리
	private static void manageCountryMenu() {
		int no = 0;
		boolean exitFlag = false;
		CountryRegisterManager ctrm = new CountryRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageCountryMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					ctrm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					ctrm.insertManager();;
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					ctrm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					ctrm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					ctrm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
		
	}

	//관리자모드 비횅기 관리
	private static void managePlaneMenu() {
		int no = 0;
		boolean exitFlag = false;
		PlaneRegisterManager prm = new PlaneRegisterManager();
		while (!exitFlag) {
			MenuViewer.managePlaneMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					prm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					prm.insertManager();;
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					prm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					prm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					prm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
	}
	
	//관리자 모드 고객 관리
	private static void manageCustomerMenu() {
		int no = 0;
		boolean exitFlag = false;
		CustomerRegisterManager crm = new CustomerRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageCustomerMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_CUSTOMER_MENU_CHOICE.SELECT:
					crm.selectManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.INSERT:
					crm.insertManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.UPDATE:
					crm.updateManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.DELETE:
					crm.deleteManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.FIND:
//					crm.findManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
		
	}


	private static boolean CustomerLoginMenu() {
		int no = 0;
		while (true) {
			MenuViewer.CustomerLoginMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case CUSTOMER_LOGIN_CHOICE.BOOKING:
				break;
				
			case CUSTOMER_LOGIN_CHOICE.MYBOOKING:
				myBookingMenu();
				break;
			case CUSTOMER_LOGIN_CHOICE.MYPAGE:
				myPageMenu();
				break;
			case CUSTOMER_LOGIN_CHOICE.FLIGHT:
				flightSearchMenu();
				break;
			case CUSTOMER_LOGIN_CHOICE.LOGOUT:
				return false;
			case MANAGER_LOGIN_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				return true;
			}
		}
		
	}
	private static void myBookingMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.myBookingMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MYBOOKING__MENU_CHOICE.CANCLE:
				break;
			case MYBOOKING__MENU_CHOICE.UPDATE:
				break;
			case MYBOOKING__MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}
		
	}
	private static void myPageMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.myPageMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MYPAGE_MENU_CHOICE.UPDATE:
				break;
			case MYPAGE_MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}
		
	}
	private static void flightSearchMenu() {
		FlightRegisterManager frm = new FlightRegisterManager();

		int no = 0;
		boolean exitFlag = false;
		frm.selectManager();
		while (!exitFlag) {
			MenuViewer.flightSearchMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case FLIGHT_SEARCH_MENU_CHOICE.SEATS:
				flightSeatsMenu();	
				break;
			case FLIGHT_SEARCH_MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}
		
	}
	private static void flightSeatsMenu() {
		SeatsRegisterManager srm = new SeatsRegisterManager();
		FlightRegisterManager frm = new FlightRegisterManager();
		BookingVO bvo = new BookingVO();
		System.out.println("좌석을 조회할 비행편 번호를 입력해주세요.");
		bvo.setFlightNo(frm.returnRightNo().getNo());
		srm.selectByFlightManager(bvo);
	}


}
