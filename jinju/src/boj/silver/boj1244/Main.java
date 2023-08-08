package boj.silver.boj1244;

import java.io.*;

public class Main {

	private static int[] switches;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchNum = Integer.parseInt(br.readLine());

		switches = new int[switchNum + 1];

		String[] temp = br.readLine().split(" ");
		for(int i = 1; i <= switchNum; i++) {
			switches[i] = Integer.parseInt(temp[i - 1]);
		}

		int commandNum = Integer.parseInt(br.readLine());

		for(int i = 0; i < commandNum; i++) {
			temp = br.readLine().split(" ");

			int command = Integer.parseInt(temp[0]);
			int key = Integer.parseInt(temp[1]);

			modifySwitches(command, key);
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= switchNum; i++) {
			sb.append(switches[i]).append(" ");

			if(i != 1 && i % 20 == 0) {
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}

	private static void modifySwitches(int command, int key) {
		if(command == 1) {
			for(int i = 1; i < switches.length; i++) {
				if(i % key == 0) {
					switches[i] = (switches[i] == 0) ? 1 : 0;
				}
			}
		} else if(command == 2) {
			switches[key] = (switches[key] == 0) ? 1 : 0;

			for(int i = 1; key - i > 0 && key + i < switches.length; i++) {
				if(switches[key - i] == switches[key + i]) {
					switches[key - i] = (switches[key - i] == 0) ? 1 : 0;
					switches[key + i] = (switches[key + i] == 0) ? 1 : 0;
				}
                else {
					break;
				}
			}
		}
	}

}