public class Drawer {
	private static void drawLine(int row, int piramidSize) {
		StringBuilder sb = new StringBuilder("");
		int xStartPos = piramidSize - row;
		for (int i = 1; i < xStartPos; i++) {
			sb.append('.');
		}
		for (int i = 0; i < (row*2+1); i++){
			sb.append('X');
		}
		for (int i = 1; i < xStartPos; i++){
			sb.append('.');
		}
		System.out.println(sb);
	}


	public static void drawPyramid(int n) {
		for (int i = 0; i < n; i++) {
			drawLine(i, n);
		}
	}

	public static void drawSegment(int n, int piramidSize) {
		for (int i = 0; i <= n; i++) {
			drawLine(i, piramidSize);
		}
	}

	
	public static void drawChristmassTree(int n) {
		for (int i = 0; i < n; i++) {
			drawSegment(i, n);
		}
	}

}
