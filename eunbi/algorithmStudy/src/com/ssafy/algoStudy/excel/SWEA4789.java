package com.ssafy.algoStudy.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA4789 {
	static String str;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			str = br.readLine();
			
			//��ü �ο���
			int total = str.length();
			//��ü�� 1���ε� 0���� �� �ڼ�ġ�� ����� 0���̸� 1�� ���, 1���̸� 0�� ���
			int result = str.charAt(0) - '0' == 0 ? 1 : 0;
			//�ڼ�ġ�� ��� 0���� �� �ڼ�ġ�� ���.
			int clapper = str.charAt(0) - '0' + result;
			
			if(str.length() == 1) {}
			else {
				for(int i = 1; i < str.length(); i++) {
					//�ڼ�ġ�� ����� ��ü �ο����� ���ų� ������ for�� ����
					if(clapper >= total) {
						break;
					}
					//�ڼ�ġ�� ����� i�� �̻��̸� �ش� ����ŭ�� ����� �ڼ��� ħ
					if(clapper >= i) {
						clapper += (str.charAt(i) - '0');
					}
					//�ڼ�ġ�� ����� i�� �̸��̸� ���ڶ�� ����ŭ ��� �� �ڼ�ġ�� ��� ���� �����ֱ�
					else {
						result += (i - clapper);
						clapper += (result + (str.charAt(i) - '0'));
					}
				}
			}
			if(clapper < total)
				result += (total - clapper);
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}

}
