package lexicalanalyser;
import java.util.*;
import java.io.*;
class LexicalAnalyzer 
{  
    static String inp,curr; 
    static char a='"';  
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    void readString() throws IOException
    {
        System.out.println("Note: Seperate every token with a whitespace");
        System.out.println("If your input is a=b+c; give the input as : a = b + c ;");
        System.out.print("Enter an input statement : ");
        inp=br.readLine();//scanning for an input string
    }
    void parseString() 
    {
        LexicalAnalyzer.isparenthesisbalanced(inp); //checking if parenthesis is balanced
        if(!inp.endsWith(";"))
        {
            System.err.println("; expected");
        }
        StringTokenizer st = new StringTokenizer(inp," ,()"); // Parsing string at occurances of " or whitespace character or , or ( or ) 
        while(st.hasMoreTokens()) 
        { 
             curr=st.nextToken();//curr identifier holds the current token
             if(curr==null)
             {
                 curr=inp;
             }
             if(LexicalAnalyzer.isKeyword(curr)) // checking if the current token is a keyword
             {
                System.out.println(curr+" : keyword");
                continue;
             }
             if(LexicalAnalyzer.isFunction(curr)) //cheking if the current token is a Function
             {
                 System.out.println(curr+" : Function");
                 continue;             
             }
             if(curr.startsWith(String.valueOf(a))) //checking if the current token is a string
             {
                String temp=null;
                int i=inp.lastIndexOf(String.valueOf(a));
                do
                {
                    
                    if(st.hasMoreTokens())
                        temp=st.nextToken();
                    i=i-temp.length();
                    
                }while(!temp.endsWith(String.valueOf(a)));
                temp=inp.substring(inp.indexOf(a),(int)inp.lastIndexOf(a)+1);
                System.out.println(temp+" : String");
                continue;
             }
             if(LexicalAnalyzer.isNumber(curr)) //checking if the current token is a number
             {
                 System.out.println(curr+" : Number");
                 continue;
             }
             if(curr.contains("+")||curr.contains("-")||curr.contains("*")||curr.contains("/")||curr.contains(">")||curr.contains("<")||curr.contains("%")||curr.contains("="))
             { 
                 LexicalAnalyzer.isOperator(curr);
                 continue;
             }
             if(curr.matches(";")) //checking if the current token is a delimiter
             {
                 System.out.println(curr+"  Delimiter");
                 continue;
             }   
             if(LexicalAnalyzer.isvalidJavaIdentifier(curr)) //checking if the current token is a valid java identifier
             {
                 System.out.println(curr+" : Valid identifier");
                 continue;
             }
             else
                System.err.println(curr+" : invalid identifier");   
         }
    }
    
    public static boolean isNumber(String temp)
    {
       try
         {
          Float.parseFloat(temp);
         }
       catch(NumberFormatException e)
        {
           return false;
        }
         return true;
    }
    public static void isparenthesisbalanced(String temp) 
    {
        int bal=0;
        for(int i=0;i<temp.length();i++)
        {
            if(temp.charAt(i)=='(')
                bal++;
            if(temp.charAt(i)==')')
                bal--;
        }
        if(bal>0)
           System.err.println("Expected "+bal+" right parenthesis");
        else if(bal<0)
        
           System.err.println("Expected "+-bal+" left parenthesis");
           
        
    }
    public static void isOperator(String temp)
    {
         switch(temp)
             {
                 case "+":
                     System.out.println(temp+" : Additon operator");
                     break;
                 case "-":
                     System.out.println(temp+" : Subtraction operator");
                     break;
                 case "*":
                     System.out.println(temp+" : Multiplication operator");
                     break;
                 case "/":
                     System.out.println(temp+" : Division operator");
                     break;
                 case "<":
                     System.out.println(temp+" : Comparision operator"); 
                     break;
                 case ">":
                     System.out.println(temp+" : Comparison operator");
                     break;
                 case "%":
                     System.out.println(temp+" : Modulo operator");
                     break;
                 case "=":
                     System.out.println(temp+" : Assignment operator");
                     break;       
             }
    }
    public static boolean isvalidJavaIdentifier(String temp)
    {
     if (temp == null || temp.length() == 0)
     {
        return false;
     }
      char[] c = temp.toCharArray();
      //for(char i:c)
        //  System.out.println(i);
    try
    {
        Integer.parseInt(String.valueOf(c[0]));
         return false;
    }
    catch(NumberFormatException e)
    {
                   
    }
    if(!(c[0]=='_'||Character.isLetter(c[0])||Character.isDigit(c[0])))
        return false;
    for (int i = 1; i < c.length; i++)
       if(!(Character.isLetter(c[i])||Character.isDigit(c[i])))
         return false;
    return true;
    }
    
  
   public static boolean isKeyword(String temp)
  {
    HashSet hs=new HashSet();
    hs.add("auto"); 
    hs.add("double");
    hs.add("int"); 
    hs.add("struct");
    hs.add("break");
    hs.add("else");
    hs.add("long");
    hs.add("switch");
    hs.add("case");
    hs.add("enum");
    hs.add("register");
    hs.add("typedef");
    hs.add("char"); 
    hs.add("extern");
    hs.add("return");
    hs.add("union");
    hs.add("continue");
    hs.add("for");
    hs.add("signed");
    hs.add("void");
    hs.add("do");
    hs.add("if");
    hs.add("static");
    hs.add("while");
    hs.add("default");
    hs.add("goto");
    hs.add("sizeof");
    hs.add("volatile");
    hs.add("const");
    hs.add("float");
    hs.add("short");
    hs.add("unsigned");
    
    return hs.contains(temp);
    }  
   public static boolean isFunction(String temp)
   {
    HashSet hs=new HashSet();
    hs.add("main");
    hs.add("printf");
    hs.add("scanf");
    hs.add("gets");
    hs.add("puts"); 
    hs.add("strcpy");	
    hs.add("strlen");
    hs.add("strcmp");
    hs.add("stricmp");	
    hs.add("strcat");
    hs.add("strstr");
    hs.add("isalpha");	
    hs.add("isdigit");
    hs.add("isupper");
    hs.add("islower");
    
    return hs.contains(temp);

   }
}
class LexicalAnalyserCode
{
  public static void main(String args[]) throws Exception
  { 
    LexicalAnalyzer la=new LexicalAnalyzer();
    la.readString();
    la.parseString();
  } 
}