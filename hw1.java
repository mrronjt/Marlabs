import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class hw1 {

    public static String reverseStr(String input){
        StringBuilder sb = new StringBuilder();
        for(int i=input.length()-1; i>=0; i--){
            sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    private static Map<String, Integer> cnt = new HashMap<String, Integer>();


    public static void cntWords(String input){
        String[] words = input.split(" ");
        for(String word : words){
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        System.out.println(cnt.entrySet());
    }

    public static void itrMap(){
        Iterator itr = cnt.entrySet().iterator();

        System.out.println("#######Q3#########\nusing While");
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("#######Q3#########using advance for loop");
        for(Map.Entry<String, Integer> entry : cnt.entrySet()){
            System.out.println(entry);
        }
    }

    public static boolean palindromeStr(String input){
        int l = 0, r = input.length()-1;
        while(l < r){
            if(input.charAt(l++) != input.charAt(r--)) return false;
        }
        return true;
    }

    public static boolean palindromeInt(int input){
        int sum = 0;
        while(sum < input){
            sum = sum * 10 + input % 10;
            if(sum == input) return true;
            input /= 10;
        }
        return input == sum;
    }
    public static void iterArrarList(List<Integer> input){
        System.out.println("for-loop");
        for (int i=0; i<input.size(); i++){
            System.out.print(input.get(i) + " ");
        }
        System.out.println("\nwhile-loop");
        Iterator itr = input.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
        System.out.println("\nadvance for-loop");
        for(int n : input){
            System.out.print(n + " ");
        }
    }

    public static void findDup(String input){
        int[] cnt = new int[26];
        for(char c : input.toCharArray()){
            cnt[c - 'a']++;
        }
        System.out.println("Assuming only lowercase characters, for input of " + input);
        System.out.println("duplicate characters are:");
        for(int i=0; i<26; i++){
            if(cnt[i] > 1){
                System.out.print((char)('a' + i) + " ");
            }
        }
    }

    public static int findSecHigh(int[] input){
        if(input.length <= 1){
            System.out.println("invalid input");
            return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(input.length, (a,b)->(b-a));
        for(int n : input) pq.add(n);
        int max = pq.peek();
        while(pq.peek() == max) pq.poll();
        return pq.poll();

    }

    public static String removeSpace(String input){
        StringBuilder sb = new StringBuilder();
        for(String word : input.split(" ")){
            sb.append(word);
        }
        return sb.toString();
    }

    public static void missingInt(int[] input){
        Map<Integer, Integer> cntInt = new HashMap<>();
        for(int n : input){
            cntInt.put(n, cntInt.getOrDefault(n, 0) + 1);
        }
        for(int n : cntInt.keySet()){
            if(cntInt.get(n) == 1) System.out.print(n + " ");
        }

    }

    public static String mostRepeatedWord(Scanner inputFile){
        Map<String, Integer> wordCnt = new HashMap<>();
        while (inputFile.hasNextLine()) {
            String data = inputFile.nextLine();
            data = data.replace('.', ' ');
            String[] words = data.split(" ");
            for(String w : words){
                wordCnt.put(w, wordCnt.getOrDefault(w, 0) + 1);
            }
        }
        String mostRep = "";
        int maxCnt = 0;
        for(String word : wordCnt.keySet()){
            if(wordCnt.get(word) > maxCnt){
                mostRep = word;
                maxCnt = wordCnt.get(word);
            }
        }
        return mostRep;
    }


    public static void main(String[] args){
        /////Q1
        System.out.println("#######Q1#########");
        System.out.println(reverseStr("MarlabsW1Q1"));

        ////Q2
        String strInput = "Marlabs Week1 Hw1 due is Sunday night night";
        System.out.println("#######Q2#########");
        cntWords(strInput);

        /////Q3
        itrMap();

        /////Q4
        System.out.println("#######Q4#########");
        String palindromStrInput = "ababc";
        System.out.println(palindromStrInput + " is palindrome?");
        System.out.println(String.valueOf(palindromeStr(palindromStrInput)));
        String palindromStrInput2 = "babab";
        System.out.println(palindromStrInput + " is palindrome?");
        System.out.println(String.valueOf(palindromeStr(palindromStrInput2)));

        int palindromNumInput = 1441;
        System.out.println(palindromNumInput + " is palindrome?");
        System.out.println(String.valueOf(palindromeInt(palindromNumInput)));

        int palindromNumInput2 = 12321;
        System.out.println(palindromNumInput2 + " is palindrome?");
        System.out.println(String.valueOf(palindromeInt(palindromNumInput2)));

        List<Integer> arrLst = new ArrayList<>();
        int tmp = palindromNumInput2;
        while(tmp > 0){
            arrLst.add(tmp % 10);
            tmp /= 10;
        }
        ////Q5
        System.out.println("#######Q5#########");
        iterArrarList(arrLst);

        ////Q6
        System.out.println("\n#######Q6#########");
        findDup(palindromStrInput);

        /////Q7
        int[] arr = new int[arrLst.size()];
        for(int i=0; i<arrLst.size(); i++){
            arr[i]  =arrLst.get(i);
        }
        System.out.println("\n#######Q7#########");
        System.out.println("Second highest number is : " + findSecHigh(arr));

        /////Q8
        System.out.println("\n#######Q8#########");
        System.out.println(removeSpace(strInput));

        ////Q9
        System.out.println("\n#######Q9#########");
        System.out.println("Missing integers in " + palindromNumInput2 + " are:");
        missingInt(arr);

        ////Q10
        System.out.println("\n#######Q10#########");
        try {
            File myObj = new File("inputForQ10.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println("The most repeated word is: ");
            System.out.println(mostRepeatedWord(myReader));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
