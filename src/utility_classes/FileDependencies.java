package utility_classes;

import java.io.File;

public class FileDependencies {
	
	public static void checkFolder(String path) {
		
		File InfoFilesDir = new File(path);
		if (InfoFilesDir.exists()) {
			System.out.println("exists");
		} else {
			System.out.println("not exists");
			InfoFilesDir.mkdir();
		}
	}
}
