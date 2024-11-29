package com.airplaneService.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.airplaneService.model.CustomerVO;

public class CustomerRegisterManager
{
	public Scanner scan = new Scanner(System.in);

	public void insertManager ()	// 1번 메뉴 <만 14세 이상 가입하기>
	{
		CustomerDAO abcdao = new CustomerDAO();

		System.out.println("*가입정보를 입력해주세요.");
		
		/////////////////////////////
		System.out.print("NO : ");
		String no = scan.nextLine();
		
		System.out.print("성명 : ");
		String name = scan.nextLine();
		
		System.out.print("생년월일(YYYYMMDD) : ");
		String birth = scan.nextLine();
		
		// ⛔추가 제약
		System.out.print("휴대폰번호 : ");
		String phone = scan.nextLine();
		
		/////////////////////////////
		/*System.out.println("REGIST : ");
		String regist = scan.nextLine();*/
		
		/////////////////////////////
		System.out.print("RIGHT : ");
		int right = Integer.parseInt(scan.nextLine());
		
		// ⛔추가 제약
		System.out.print("아이디(8자리@이메일.주소) : ");
		String id = scan.nextLine();
		
		// ⛔추가 제약
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		
		///////////////////////////////
		/*System.out.println("COUNT : ");
		int count = Integer.parseInt(scan.nextLine());*/

		CustomerVO abcvo = new CustomerVO(no, name, birth, phone, right, id, pw);
		
		boolean successFlag;
		try
		{
			successFlag = abcdao.insertDB(abcvo);
			if (successFlag == true)
			{
				System.out.println(name + "님의 가입이 완료되었습니다.");
			} else
			{
				System.out.println(name + "님의 가입이 완료되지않았습니다.");
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}

	public void findManager ()	// 3번 메뉴 <내 가입정보 확인하기> - 아이디, 휴대폰번호 입력받는 조건.
	{		
		CustomerDAO abcdao = new CustomerDAO();
		ArrayList <CustomerVO> CustomerVOList = new ArrayList <CustomerVO>();

		System.out.print("조회할 아이디를 입력해주세요 : ");
		String id = scan.nextLine().trim();
		
		System.out.print("가입 시 등록한 휴대폰번호를 입력해주세요 : ");
		String phone = scan.nextLine().trim();
		
		CustomerVOList = abcdao.selectCheckDB(id, phone);
		
		if (CustomerVOList == null)
		{
			CustomerVO.getHeader();
			System.out.println("출력할 데이터가 존재하지 않습니다.");
			return;
		}
		printCustomerVOList(CustomerVOList);
	}

	public void updateManager ()	// 4번 메뉴 <내 가입정보 수정하기>
	{
		CustomerDAO abcdao = new CustomerDAO();
		
		System.out.print("가입하신 아이디를 입력하세요 : ");
		String id = scan.nextLine().trim();

		System.out.print("수정할 이름을 입력하세요 : ");
		String name = scan.nextLine().trim();
		
		System.out.print("수정할 생년월일을 입력하세요(YYYYMMDD) : ");
		String birth = scan.nextLine().trim();
		
		System.out.print("수정할 휴대폰번호를 입력하세요 : ");
		String phone = scan.nextLine().trim();
		
		CustomerVO abcvo = new CustomerVO();
		
		boolean successFlag;
		
		abcvo = new CustomerVO(name, birth, phone, id);
		
		try
		{
			successFlag = abcdao.updateDB(abcvo);
			// 화면출력.
			if (successFlag == true)
			{
				System.out.println(name + "님의 회원정보 수정이 완료되었습니다!");
			} else
			{
				System.out.println(name + "님의 회원정보 수정이 완료되지 않았습니다!");
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}
	
	public void deleteManager ()	// 5번 메뉴 <회원 탈퇴하기>
	{
		CustomerDAO abcdao = new CustomerDAO();

		CustomerVO abcvo = new CustomerVO();
		
		System.out.print("탈퇴할 아이디를 입력해주세요 : ");
		String id = scan.nextLine().trim();
		
		System.out.print("가입 시 등록한 휴대폰번호를 입력해주세요 : ");
		String phone = scan.nextLine().trim();

		abcvo.setId(id);
		abcvo.setPhone(phone);
		boolean successFlag = abcdao.deleteDB(abcvo);
		
		if (successFlag == true)
		{
			System.out.println("아이디 " + id + "님의 회원 탈퇴가 완료되었습니다.");
		} else
		{
			System.out.println("아이디 " + id + "님의 회원 탈퇴가 완료되지 않았습니다.");
		}
	}
	
	public void selectCountManager ()
	{
		CustomerDAO abcdao = new CustomerDAO();
		
		CustomerVO abcvo = new CustomerVO();
		
		String print = 	abcdao.selectCountDB();
		System.out.print(print);

	}
	
	private void printCustomerVOList(ArrayList <CustomerVO> CustomerVOList)	// 출력메소드.
	{
		System.out.println(CustomerVO.getHeader());
		for (CustomerVO p : CustomerVOList)
		{
			System.out.println(p);
		}
	}
	
	public void selectManager ()	// <가입자 전체 정보 출력.>
	{
		CustomerDAO abcdao = new CustomerDAO();
		ArrayList <CustomerVO> airBookingCustomerVOList = new ArrayList <CustomerVO>();
		
		airBookingCustomerVOList = abcdao.selectDB();
		
		if (airBookingCustomerVOList == null)
		{
			System.out.println("출력할 데이터가 존재하지 않습니다.");
			return;
		}
		printCustomerVOList(airBookingCustomerVOList);
		
	}
	
	
}
