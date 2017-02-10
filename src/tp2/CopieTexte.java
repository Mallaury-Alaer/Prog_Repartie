package tp2;

import java.io.*;

public class CopieTexte {
	public CopieTexte(String src, String dst) throws IOException{
		File d = new File(dst);
		if(!d.exists()){
			BufferedReader source = new BufferedReader(new FileReader(src));
			BufferedWriter dest = new BufferedWriter(new FileWriter(d));
		

			try {
				
				int n = 0;
				while((n=source.read())>=0){
					dest.write(n);
					
				}
				source.close();
				dest.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("Le fichier de destination existe deja !");
		}
	}
			
	
	
	public static void main(String[] args) throws IOException{
		new CopieTexte("fichiers/test.txt", "fichiers/destination.txt");
	}
}
