package imagealgo;

import java.io.IOException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Formatter;
import java.io.FileReader;  
import java.io.FileWriter;
import java.util.stream.IntStream;
import java.text.DecimalFormat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;


public class imagealgo{

    //private static final String NEW_LINE = System.lineSeparator();
    private static final String UNKNOWN_CHARACTER = ".";
    protected static final String algo = null;

    public static void mainer(String filename,String random_val,String key) throws IOException {

        String file = filename;
        String numstr="";
        String s = convertFileToHex(Paths.get(file));
        try{    
            FileWriter fw=new FileWriter("f.txt.txt");    
            for(int i=0;i<s.length();i=i+2)
            {
            String hex=s.substring(i, i+2);  
            int decimal=Integer.parseInt(hex,16); 
            numstr=numstr+String.valueOf(decimal)+" "; 
             }
           fw.write(numstr);    
            fw.close();    
           }catch(Exception e){System.out.println(e);}    
          // System.out.println("check f.txt.txt");
        //System.out.println(s);
        //long start= System.currentTimeMillis();
        String key1 =key;
        int random =Integer.parseInt(random_val);

		DecimalFormat df = new DecimalFormat("0000000");
		//DecimalFormat df1 = new DecimalFormat("000000");
        int[] arr = IntStream.rangeClosed(0, 127).toArray();
        
			
       char chararray[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f',
       'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','/','\\','\'',' ',
	   'x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S',
       'T','U','V','W','X','Y','Z','!','"','#','$','%','&','(',')','*','+',',','-','.',':',';',
       '<','=','>','?','@','[',']','^','_','`','{','|','}','~'};
       String charstr=new String(chararray);
		char chartab2[]={'5',';','<','=','>','?','@','6','7','O','P','Q','R','S','8','9','a','b',
        'x','A','B','C','D','E','l','m','n','I','J','o','p','q','r','F','G','H','K','y','z','A','c','d','e','f','!','"','#','$',
        '%','&','(','W','j','k','s','t','u','v','w','0','1','2','3','4'};
	   leftRotate(arr, random, arr.length);
       System.out.println("reading table1....");
      /* for (int i = 0; i < arr.length; i++)
	    {   
			System.out.println(df.format(Integer.parseInt(Integer.toBinaryString(arr[i]))));
		}*/
        System.out.println("reading table2....");
         /*for (int i = 0; i < table2.length; i++)
	    {   
			System.out.print(df1.format(Integer.parseInt(Integer.toBinaryString(table2[i])))+"-"+chartab2[i]+"  ");
		}*/
       int k=0;
        char strar[]=new char[10000000];
        try{
        FileReader fr=new FileReader("f.txt.txt");    
          int i;    
          while((i=fr.read())!=-1)    
           { 
             strar[k]=(char)i;
             k++; 
           }   
          fr.close(); 
        }
        catch(Exception e)  
        {  
        //e.printStackTrace();  
         }  
         int evn =   0,odd =   0, len =   key1.length();

     char   strE[]  =   new char[7],  strO[] =    new char[7];

     for(int j=0 ; j<len ; j++)
     {
         if(j%2 == 0)
         {
             strE[evn]   =   key1.charAt(j);
             evn++;
         }
         if(j%2 == 1)
         {
             strO[odd]   =  key1.charAt(j);
             odd++;
         }
     }
      
      // System.out.print("plain text: ");
      //System.out.println(strar);
      /* System.out.println(strE);
       System.out.println(strO);*/
       int o=0;
       int cip[]=new int[k];
      for(int i=0;i<k;i++)
        {
        if(key1.charAt(0)=='0' && key1.charAt(1)=='0')
           { o=1;
             String stre=new String(strO);
             int ind =charstr.indexOf(strar[i]);
             if(ind!=-1)
             cip[i]=arr[ind] ^ Integer.parseInt(stre,2);
          
            }
       else if(key1.charAt(0)=='1' && key1.charAt(1)=='0')
        { 
            String stre=new String(strE);
           int ind =charstr.indexOf(strar[i]);
           if(ind!=-1)
           cip[i]=arr[ind] ^ Integer.parseInt(stre,2);
          
        }
        else if(key1.charAt(0)=='1' && key1.charAt(1)=='1'||key1.charAt(0)=='0' && key1.charAt(1)=='1')
        { 
          String stre1=new String(strE);
          String stre2=new String(strO);
           int ind =charstr.indexOf(strar[i]);
           if(ind!=-1)
           cip[i]=arr[ind] ^ (Integer.parseInt(stre1,2)^Integer.parseInt(stre2,2));
          
        }
       else
       System.out.println("key error!");
       }
       
      // for(int i=0;i<cip.length;i++)
          // System.out.println(cip[i]);
       String S="";
      for(int i=0;i<k;i++)
        if((127-(cip[i]+random))%128 != -1)
         cip[i]= arr[(127-(cip[i]+random))%128];
     // for(int i=0;i<cip.length;i++)
        // System.out.println(cip[i]);
      for(int i=0;i<k;i++)
        S = S + (df.format(Integer.parseInt(Integer.toBinaryString(cip[i]))));
  //System.out.println(S);
   String s1=new String(S.substring(0, (S.length()/2)));
   String s2=new String(S.substring((S.length()/2),S.length()));
  // System.out.println(s1);
   //System.out.println(s2);
   s2=(rightotate(s2,1));
   s1=(leftotate(s1,1));
   S=(s2+s1);
   while((S.length())%6!=0)
   {
     S=S+"0";
   }
 // System.out.println(S);
   String cipertxt="";
   for(int i=0;i<S.length();i=i+6)
       {
        cipertxt= cipertxt + (chartab2[Integer.parseInt(S.substring(i,i+6),2)]);
       }
   //System.out.print("cipher text:");
  // System.out.println(cipertxt);
   System.out.print("length of string:");
   System.out.println(cipertxt.length());
   try{    
    FileWriter fw=new FileWriter("f.txt.txt");    
    fw.write(cipertxt);    
    fw.close();   
   }catch(Exception e){System.out.println(e);}   
   //System.out.println("check encrypted.txt");   
   //long end= System.currentTimeMillis();
   //System.out.println(end-start); 
   String str = "f.txt.txt";
   String ascii = method(str);
 

   // Step-1 - Convert ASCII string to char array
   char[] ch = ascii.toCharArray();

   StringBuilder builder = new StringBuilder();
   for (char c : ch) {
      // Step-2 Use %H to format character to Hex
      String hexCode=String.format("%H", c);
      builder.append(hexCode);
     
   }
    //System.out.println("ASCII = " + ascii);
    try{    
      FileWriter fw=new FileWriter("hexa.txt");    
      fw.write(builder.toString());    
      fw.close();    
     }catch(Exception e){System.out.println(e);}    
    //System.out.println(builder.toString());
    String stry = "hexa.txt";
    String hex = method(stry);
    int oo= 45;
    FileInputStream fis = new FileInputStream(filename);					
    byte data[] = new byte[fis.available()];
    fis.read(data);
    int i = 0;
    for (byte b : data) {
        data[i] = (byte)(b ^ oo);
        i++;
    }				
    FileOutputStream fos = new FileOutputStream("im3.png");	
    fos.write(data);
    fos.close();
    fis.close();
    System.out.println("check image file im3.png");
    System.out.println("image encryption done!!");

  
    }
    public static String toHex(int decimal){    
        int rem;  
        String hex="";   
        char hexchars[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};  
       while(decimal>0)  
        {  
          rem=decimal%16;   
          hex=hexchars[rem]+hex;   
          decimal=decimal/16;  
        }  
       return hex;  
    } 
    public static byte[] HexStringToByteArray(String hexStr) {
        int len = hexStr.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexStr.charAt(i), 16) << 4)
                    + Character.digit(hexStr.charAt(i + 1), 16));
        }
        return data;
    } 
        static String leftotate(String str, int d)
        {
                String ans = str.substring(d) + str.substring(0, d);
                return ans;
        }
     
        // function that rotates s towards right by d
        static String rightotate(String str, int d)
        {
                return leftotate(str, str.length() - d);
        }
            
    static void leftRotate(int arr[], int d, int n)
	{
		
		int temp[] = new int[d];

		for (int i = 0; i < d; i++)
			temp[i] = arr[i];

		for (int i = d; i < n; i++) {
			arr[i - d] = arr[i];
		}

		for (int i = 0; i < d; i++) {
			arr[i + n - d] = temp[i];
		}
  }

    public static String convertFileToHex(Path path) throws IOException {

        if (Files.notExists(path)) {
            throw new IllegalArgumentException("File not found! " + path);
        }

        StringBuilder result = new StringBuilder();
        StringBuilder hex = new StringBuilder();
        StringBuilder input = new StringBuilder();

        int count = 0;
        int value;

        // path to inputstream....
        try (InputStream inputStream = Files.newInputStream(path)) {

            while ((value = inputStream.read()) != -1) {

                hex.append(String.format("%02X", value));

                //If the character is unable to convert, just prints a dot "."
                if (!Character.isISOControl(value)) {
                    input.append((char) value);
                } else {
                    input.append(UNKNOWN_CHARACTER);
                }

                // After 15 bytes, reset everything for formatting purpose
                if (count == 14) {
                    result.append(String.format("%s", hex));
                    hex.setLength(0);
                    input.setLength(0);
                    count = 0;
                } else {
                    count++;
                }

            }

            // if the count>0, meaning there is remaining content
            if (count > 0) {
                result.append(String.format("%s", hex));
            }

        }

        return result.toString();
    }
    private static String method(String filePath)
    {
 
        // Declaring object of StringBuilder class
        StringBuilder builder = new StringBuilder();
 
        // try block to check for exceptions where
        // object of BufferedReader class us created
        // to read filepath
        try (BufferedReader buffer = new BufferedReader(
                 new FileReader(filePath))) {
 
            String str;
 
            // Condition check via buffer.readLine() method
            // holding true upto that the while loop runs
            while ((str = buffer.readLine()) != null) {
 
                builder.append(str).append("\n");
            }
        }
        catch (IOException e) {
  
          // Print the line number here exception occured
          // using printStackTrace() method
          e.printStackTrace();
      }
 
      // Returning a string
      return builder.toString();
  }

  public static void main(String args[]) {
    try{
    mainer("im7.png","1","10101010101010");}
    catch(Exception e){}
  }

}