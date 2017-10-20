import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("all")
public /*abstract*/ class PropertiesUtil {
	
//	public abstract boolean equals(Object obj);
	
	private static List<InputStream> loadFiles(String path) {
		File filePath = new File(path);
		File zhFiles[] = filePath.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				boolean isAccept = false;
				if(name.contains("zh_CN")) {
					isAccept = true;
				}
				return isAccept;
			}
		});
		for(File file : zhFiles) {
//			System.out.println(file.getName());
			try(InputStream input = new FileInputStream(file)) {
				Properties p = new Properties();
				p.load(input);
				System.out.println(file.getName() + " : " + p.size());
				Map<String, String> dict = new LinkedHashMap();
				p.forEach((key, value) -> System.out.println(key + " = " + value));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		PropertiesUtil.loadFiles("D:/IBM/WCDE_ENT70/workspace/Stores/src/AvnetSAS");
	}

}
