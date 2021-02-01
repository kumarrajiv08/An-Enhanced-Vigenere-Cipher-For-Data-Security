import java.util.Scanner;


public class VigenereCipher {

    public static void main(String[] args) {

        int up; up=0;
        System.out.println("\n\n\t\t\t\t\t\t\tWELCOME!!!!!!!!!!!!");
        System.out.println("\t\t\t\t\t     AN IMPLEMENTATION OF ENHANCED VIGENERE CIPHER\n");

        Scanner s = new Scanner(System.in);
        char[][] Mat = new char[8][27];
        char[] Alpha = new char[27];

        for (int i = 0; i < 26 ; i++) {
            Alpha[i] = (char)('A' + i);    // char array of A,B,C....Z,&;
        }
        Alpha[26] = '&';

        int index = 1;

        for(int i = 0; i < 8; i++){               //logic for filling Mat
            for(int j = 0;j < 27; j++){
                int r = (index + j) % 27;
                Mat[i][j] = Alpha[r];
            }
            index = index + 2;
        }

        System.out.println("Enter the Plain Text: ");
        String pt = s.nextLine();
        int o;o=0;
        while(o<pt.length()-1)
        {
            if((int)pt.charAt(o)>=65 && (int)pt.charAt(o)<=90)
            {
                up=1;           //for checking uppercase/lowercase
                break;
            }
            o++;
        }

        pt=pt.toUpperCase();

        System.out.println("\nEnter the Key without spaces: ");
        String kt =  s.nextLine();
        kt=kt.toUpperCase();
        if(pt.length()<kt.length())
        {
            System.out.println("\nEntered Length Of Key Must be less than PLAIN TEXT!!   QUITING..... \n");
            System.exit(0);
        }
        for(int i = 0; i < pt.length(); i++){
            if(pt.charAt(i) == ' ')
                pt = pt.substring(0,i) + '&' + pt.substring(i+1);            //for replacing spaces ' '  with & in plain text
        }



        index = 0;
        int ktlen = kt.length();

        while(kt.length() != pt.length()){       //for making kt length = pt length
            kt = kt + kt.charAt(index % ktlen);
            index++;
        }


        String ct = Encrpytion(pt,kt,Mat);   		//ct contains Cipher Text

        System.out.println("\nWould like to decrypt the text? (Y/N)");
        char deci=s.next().charAt(0);

        if(deci=='Y' || deci == 'y')
        {
            try
            {
                System.out.println("");
                System.out.print("\t\t\t\t\t\t DECRYPTING......");
                Thread.sleep(2000);
                System.out.print(".......");
                Thread.sleep(2000);
                System.out.print(".......");

                Thread.sleep(500);
                System.out.print("DONE!!");
                Thread.sleep(500);
                System.out.println("\n");
            }
            catch(InterruptedException e)
            {
                //Leave it
            }

            Decryption(ct,kt,Mat,up);
        }
        else{
            System.out.println("\n\t\t\t\t\t\t\tTHANK YOU!!\n\n ");
        }

    }

    public static String Encrpytion(String pt, String kt ,char[][] Mat) {


        String ct ="";
        int index = 0;

        for (int i = 0; i < pt.length() ; i++) {
            char charpt = pt.charAt(i);                        //char of plain text
            char charkt = kt.charAt(i);			    //char of key text
            int charpt_index = 0;
            int charkt_index = 0;

            for(int j = 0; j < 27; j++){
                if( Mat[index % 8][j] == charpt ){              //finds index of plain text's char in Mat and stores in charpt_index
                    charpt_index = j;
                } if( Mat[index % 8][j] == charkt){         //finds index of key text's char in Mat and stores in charkt_index
                    charkt_index = j;
                }
            }

            int cipher_index = (charkt_index + charpt_index) % 27;

            ct = ct + Mat[index % 8][cipher_index];              //adding char in cipher string

            index++;                                           //incrementing rows
        }
        //int delay = 1000; // number of milliseconds to sleep

        //long start = System.currentTimeMillis();
        //while(start >= System.currentTimeMillis() - delay); // do nothing

        //System.out.println("Time Slept: " + Long.toString(System.currentTimeMillis() - start));

        try
        {
            System.out.println("");
            System.out.print("\t\t\t\t\t\t ENCRYPTING......");
            Thread.sleep(2000);
            System.out.print(".......");
            Thread.sleep(2000);
            System.out.print(".......");
            Thread.sleep(500);
            System.out.print("DONE!!");
            Thread.sleep(500);
            System.out.println("");
        }
        catch(InterruptedException e)
        {
            //Leave it
        }

        //Thread.sleep(4000);

        System.out.println("\nCipher text ------>  " + ct +"\n");

        return ct;

    }

    public static void Decryption(String ct,String kt,char[][]Mat, int up1){

        int index = 0;
        String res = "";
        for (int i = 0; i < ct.length() ; i++) {
            char charct = ct.charAt(i);
            char charkt = kt.charAt(i);
            int charct_index = 0;
            int charkt_index = 0;


            for(int j = 0; j < 27; j++){
                if( Mat[index % 8][j] == charct){              //finds index of plain text in Mat and stores in charpt_index
                    charct_index = j;
                } if( Mat[index % 8][j] == charkt){         //finds index of key text in Mat and stores in charkt_index
                    charkt_index = j;
                }
            }

            int res_index = (charct_index - charkt_index);
            if(res_index < 0) res_index = res_index + 27;

            res_index = res_index % 27;

            if(Mat[index % 8][res_index] == '&') res = res + ' ';
            else res = res + Mat[index % 8][res_index];


            index++;
        }
        // IGNORE -- System.out.println("Op "+up1+"\n");
        if(up1==1)
        {

            res=res.toUpperCase();
        }
        else if(up1==0){

            res=res.toLowerCase();

        }
        System.out.println("Decrypted text ------->  " + res + "\n\n");

    }


}