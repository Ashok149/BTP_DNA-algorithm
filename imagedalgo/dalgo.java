package imagedalgo;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Formatter;
import java.io.FileReader;  
import java.io.FileWriter;

public class dalgo
{
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
    public static void mainert(String filename,String key,String rand)
    {
        //System.out.print("enetr 14bit key sequence:");
        String key1 =key;
       // System.out.print("enetr random number:");
        int random =Integer.parseInt(rand);
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
        FileReader fr=new FileReader(filename);    
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
     System.out.println("check f.txt.txt");   
  
}
public static void main(String[] args) {
  mainert("encrypted.txt", "10101010101010", "2");
}

}