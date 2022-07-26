package imagedalgo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Formatter;
import java.io.FileReader;  
import java.io.FileWriter;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.text.DecimalFormat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class decrypt {
   private static final String UNKNOWN_CHARACTER = ".";
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
  public static byte[] HexStringToByteArray(String hexStr) {
   int len = hexStr.length();
   byte[] data = new byte[len / 2];
   for (int i = 0; i < len; i += 2) {
       data[i / 2] = (byte) ((Character.digit(hexStr.charAt(i), 16) << 4)
               + Character.digit(hexStr.charAt(i + 1), 16));
   }
   return data;
}
  static String decTohex(int n)
    {
        // Creating an array to store octal number
        int[] hexNum = new int[100];
 
        // counter for hexadecimal number array
        int i = 0;
        while (n != 0) {
 
            // Storing remainder in hexadecimal array
            hexNum[i] = n % 16;
            n = n / 16;
            i++;
        }
 
        // Printing hexadecimal number array
        // in the reverse order
        for (int j = i - 1; j >= 0; j--) {
            if (hexNum[j] > 9)
                return Character.toString((char)(55 + hexNum[j]));
            else
                return  Integer.toString((hexNum[j]));
        }
        return "";
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
    public static void mainer(String filename,String random_val,String key) throws IOException {
      String file = filename;
      String numstr="";
      //Scanner sc =new Scanner(System.in);
      //System.out.print("enter 14bit key sequence:");
      String key1 =key;
     // System.out.print("enter random number:");
      int random =Integer.parseInt(random_val);
      String s = convertFileToHex(Paths.get(file));
      try{    
          FileWriter fw=new FileWriter("hex.txt");    
         
          for(int i=0;i<s.length();i=i+2)
          {
         
            String hex=s.substring(i, i+2);  
          
            int decimal=Integer.parseInt(hex,16); 
          
            numstr=numstr+String.valueOf(decimal)+" "; 
           }
           fw.write(s);    
          fw.close();    
         }
         catch(Exception e)
           {System.out.println(e);}
      String str = "hexa.txt";
      String hex = method(str);
    // System.out.println("Hex = " + hex);
      if(hex.length()%2!=0){
         System.err.println("Invlid hex string.");
         return;
      }
      
      StringBuilder builder = new StringBuilder();
    try{
      for (int i = 0; i < hex.length(); i = i + 2) {
         // Step-1 Split the hex string into two character group
         String s2 = hex.substring(i, i + 2);
         // Step-2 Convert the each character group into integer using valueOf method
         int n = Integer.valueOf(s2, 16);
         // Step-3 Cast the integer value to char
         builder.append((char)n);
      }}
      catch(NumberFormatException n)
      {
         System.out.println(n);
      }

     // System.out.println("Hex = " + hex);
      System.out.println("ASCII = " + builder.toString());
     try{    
      FileWriter fw=new FileWriter("hexatoascii.txt");    
      fw.write(builder.toString());    
      fw.close();    
     }catch(Exception e){//System.out.println(e);
   } 
  
//	DecimalFormat df = new DecimalFormat("0000000");
 DecimalFormat df1 = new DecimalFormat("000000");
   int[] arr = IntStream.rangeClosed(0, 127).toArray();
 int[] table2 = IntStream.rangeClosed(0, 63).toArray();
  char chararray[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f',
  'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','/','\\','\'',' ',
 'x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S',
  'T','U','V','W','X','Y','Z','!','"','#','$','%','&','(',')','*','+',',','-','.',':',';',
  '<','=','>','?','@','[',']','^','_','`','{','|','}','~'};
  String charstr=new String(chararray);
  while(charstr.length()<arr.length)
  {
   charstr=charstr +" ";
  }
 // System.out.println(charstr.length());
 char chartab2[]={'5',';','<','=','>','?','@','6','7','O','P','Q','R','S','8','9','a','b',
   'x','A','B','C','D','E','l','m','n','I','J','o','p','q','r','F','G','H','K','y','z','A','c','d','e','f','!','"','#','$',
   '%','&','(','W','j','k','s','t','u','v','w','0','1','2','3','4'};
 // String charstr2=new String(chartab2);
 leftRotate(arr, random, arr.length);
   int k=0;
   char strar[]=new char[10000000];
   try{
   FileReader fr=new FileReader("hexatoascii.txt");    
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
    String S="";
    String ss=new String(chartab2);
   int num[]=new int[k];
  for(int i=0;i<k;i++)
  {
      num[i]=table2[ss.indexOf(strar[i])];
  }
  //for(int i=0;i<k;i++)
       //System.out.println(num[i]);
  for(int i=0;i<k;i++)
    S=S+df1.format(Integer.parseInt(Integer.toBinaryString(num[i])));
// System.out.println(S);
S= S.substring(0, S.length()-(S.length()%7));
// System.out.println(S);
String s1="";
String s2="";
if(S.length()%2 == 0)
{
   s1=new String(S.substring(0, ((S.length()/2))));
   s2=new String(S.substring((S.length()/2),S.length()));
 }
else
{
  s1=new String(S.substring(0, ((S.length()/2)+1)));
  s2=new String(S.substring((S.length()/2)+1,S.length()));
}
// System.out.println(s1);
//System.out.println(s2);
s2=(rightotate(s2,1));
s1=(leftotate(s1,1));
// System.out.println(s1);
// System.out.println(s2);
S=(s2+s1);
// System.out.println(S);
int jp=0;
int arre[]=new int[(S.length())/7];
for(int i=0;jp<S.length();i++)
{
 arre[i]=(127-(Integer.parseInt(S.substring(jp, jp+7),2)))%128;
 jp=jp+7;
}
/* for(int i=0;i<arre.length;i++)
{
System.out.println(arre[i]);
}*/
int evn =   0,
odd =   0,
len =   key1.length();

char   strE[]  =   new char[7],
    strO[] =    new char[7];

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


/* System.out.println(strE);
System.out.println(strO);*/


String D="";
try{
for(int i=0;i<arre.length;i++)
   {
   if(key1.charAt(0)=='0' && key1.charAt(1) == '0')
      { 
        String stre=new String(strO);
       D=D+charstr.charAt(((arre[i]^Integer.parseInt(stre,2))-random)%128);
       }
  else if(key1.charAt(0)=='1' && key1.charAt(0)=='0')
   {
     String stre=new String(strE);
      D=D+charstr.charAt(((arre[i]^Integer.parseInt(stre,2))-random)%128);
   }
   else if(key1.charAt(0)=='1' && key1.charAt(0)=='1'|| key1.charAt(0)=='0' && key1.charAt(0)=='1' )
   {
     String stre1=new String(strE);
     String stre2=new String(strO);
      D=D+charstr.charAt(((arre[i]^(Integer.parseInt(stre1,2)^Integer.parseInt(stre2,2)))-random)%128);
   }
  else
  System.out.println("key error!");
  }}
  catch(Exception e)  
   {  
   //e.printStackTrace();  
    } 
System.out.println(D);
try{    
 FileWriter fw=new FileWriter("f.txt.txt");    
 fw.write(D);    
 fw.close();    
}catch(Exception e){System.out.println(e);}    
//System.out.println("check f.txt.txt"); 

BufferedReader br2 = null;
int nume[] = new int[10000000];
String[] strNums;
StringBuilder hext = new StringBuilder();
try{
br2 = new BufferedReader(new FileReader("f.txt.txt"));
strNums = br2.readLine().split(" ");
for (int i = 0; i < strNums.length; i++) {
 num[i] = Integer.parseInt(strNums[i]);
}
for (int i = 0; i < strNums.length; i++) {
 hext.append(String.format("%02X", nume[i]));

}
// System.out.println(hex);
try{    
 FileWriter fw=new FileWriter("f.txt.txt");    
 fw.write(hext.toString());    
 fw.close();    
}catch(Exception e){System.out.println(e);}    
//Scanner sc = new Scanner(System.in);
		int oo = 45;
		FileInputStream fis = new FileInputStream(filename);
		byte data[] = new byte[fis.available()];
		
		fis.read(data);
		int i = 0;
		for (byte b : data) {
			data[i] = (byte)(b^oo);
			i++;
		}
    
		
		FileOutputStream fos = new FileOutputStream("im2.png");
		
		fos.write(data);
		fos.close();
		fis.close();
		System.out.println("check image file im2.png");

}
catch(Exception e){
// System.out.println(e);
}


   }
   public static void main(String args[]) {
    try{
    mainer("im3.png","2","10101010101010");}
    catch(Exception e){}
  }
}

