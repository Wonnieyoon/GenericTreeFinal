

import java.io.File;

import kr.sys4u.test.Main;

public class FileDirectoryMain {

	//Soc : Separation of Concern
	//고려해야할것들을 분리해라
	
	//Data Conversion을 수행한다.
	//args에는 다음과 같이 값이 입력되어야한다.
	//index0 : DIR Path
	//index1 : Response class name
	//index2 : Converter class name
	//@param args
	
	//c:/"" kr.sys4u.directory.Convertable java.lang.String
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
//		/FileTreeFileConverter ff = new FileTreeFileConverter();
		//ff.setFileTree(new FileTree("C:/Test1"));
		//ff.convert(new FileTree("C:/Test1"));
		
	
		ConversionManager manager = new ConversionManager();
		manager.addConverter(FileTree.class,String.class, new FileTreeStringConverter());
		String str = manager.requestConvert(new FileTree("C:\\Coredax"),String.class );
		System.out.println(str);
		
		manager.addConverter(FileTree.class, File.class, new FileTreeFileConverter("C:/TreeResult/test.txt"));
		manager.requestConvert(new FileTree("C:/Coredax"), File.class);
		
		//File IO
		//manager.addConverter(File.class, new FileTreeFileConverter("c:/temp/file-structure.txt"));
		
		//String result = manager.requestConvert(new FileTree("C:\\Coredax"), String.class);
		//sSystem.out.println(result);
		
//		if(args.length<3) {
//			throw new IllegalArgumentException("Argument 형식이 맞지 않습니다. API를 참조하세요");
//		}
//		
//		String dirPath = args[0];
//		String converterClassName = args[1];
//		String responseClassName = args[2];		
//		
//		final String directory = dirPath;		
//		final FileTree fileTree = new FileTree(directory);
//		ConversionManager manager = new ConversionManager();		
//		
//		Class<?> resultType = createClass(converterClassName);
//		Class<?> converterClassType = createClass(responseClassName);
//		Convertable<?,?> converter = (Convertable<?,?>)converterClassType.newInstance(); //생성자가 없거나 private로 되어있거나 등등
//				
//		manager.addConverter(resultType,converter);
//		Object str = manager.requestConverter(fileTree, resultType);
//		System.out.println(str);
		
		//---------------------
//		String dirPath = "C:/TreeResult";
//		String converterClassName = "kr.sys4u.directory.Convertable";
//		String responseClassName = "java.lang.String";
//		
//		final String directory = dirPath;		
//		final FileTree fileTree = new FileTree(directory);
//		ConversionManager manager = new ConversionManager();	
//		
//		
//		Class<?> resultType = createClass(converterClassName);
//		Class<?> converterClassType = createClass(responseClassName);
//		Convertable<?,?> converter = (Convertable<?,?>)converterClassType.newInstance(); //생성자가 없거나 private로 되어있거나 등등
//				
//		manager.addConverter(resultType,converter);
//		Object str = manager.requestConverter(fileTree, resultType);
				
		
//		
//		System.out.println(str);
	}

	@SuppressWarnings("unused")
	private static Class<?> createClass(String className){
		Class<?> resultType = null;
		try {
			resultType = FileDirectoryMain.class.getClassLoader().loadClass(className); // 인스턴스 만드는 방법 3가지 new , clone , forName, loadClass
			//Class.forName(className)			
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("No Such class: "+className);
		}
		return resultType;
	}
	
	
}
