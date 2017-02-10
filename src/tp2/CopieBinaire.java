package tp2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopieBinaire {
	
	public static void main(String[] args){
		InputStream in;
		BufferedInputStream bis;
		OutputStream ot;
		BufferedOutputStream bos;
		File src;
		File dest;
		
		if(args.length != 2){
			System.out.println("Nombre d'arguments différents de 2 !");
			return;
		}
		src = new File(args[0]);
		dest = new File(args[1]);
		if(src.exists() && !dest.exists()){

			try{
				in = new FileInputStream(src);
				bis = new BufferedInputStream(in);
				ot = new FileOutputStream(dest);
				bos = new BufferedOutputStream (ot);
				byte buffer[] = new byte[8];
				while((bis.read(buffer)) >=0){
					bos.write(buffer);
				}
				in.close();
				bos.close();
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
		}else{
			System.out.println(args[0]+" n'existe pas || "+args[1]+" existe !");
		}
	}

}
