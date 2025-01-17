import java.util.*;

public class cutStrategy{

    public static void print(int ar[]){
        for(int ele: ar)
            System.out.print(ele+" ");
        System.out.println();
    }

    public static void print2D(int arr[][]){
        for(int ar[]: arr)
            print(ar);
        System.out.println();
    }

// GFG:- Matrix Chain Multiplication(MCM) https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/

    public static int mcm_memo(int arr[],int si,int ei,int dp[][]){
        if(si+1 == ei) // for single left matrix cost of multiplication is 0
            return dp[si][ei] = 0;

        if(dp[si][ei] != -1)
            return dp[si][ei];
        
        int minCost = (int)1e9;
        for(int cut = si+1; cut < ei; cut++){
            int leftAns = mcm_memo(arr,si,cut,dp);
            int rightAns = mcm_memo(arr,cut,ei,dp);

            int myAns = leftAns+ (arr[si]*arr[cut]*arr[ei]) + rightAns;
            minCost = Math.min(minCost,myAns);
        }

        return dp[si][ei] = minCost;
    }

    public static int mcm_dp(int arr[],int SI,int EI,int dp[][]){
        int n = arr.length;

        // loop similar to the one in palindrome questions 
        for(int gap = 1;gap < n; gap++){
            for(int si = 0,ei = gap;ei < n;si++,ei++){
                if(si+1 == ei){ // for single left matrix cost of multiplication is 0
                 dp[si][ei] = 0;
                 continue;
                }
       
        int minCost = (int)1e9;
        for(int cut = si+1; cut < ei; cut++){
            int leftAns = dp[si][cut];//mcm_memo(arr,si,cut,dp);
            int rightAns = dp[cut][ei];//mcm_memo(arr,cut,ei,dp);

            int myAns = leftAns+ (arr[si]*arr[cut]*arr[ei]) + rightAns;
            minCost = Math.min(minCost,myAns);
        }

        dp[si][ei] = minCost;
        }
        }

        return dp[SI][EI];
    }

// MCM follow up, suppose the cost of each multiplication is 5 rs. & cost of each addition is 8 rs.
// Total cost for matrix creation = (5*q+8(q-1))*p*r
// Assumed total number of multiplications is q and thus no. of addition is one less

    public static int mcm_memo1(int arr[],int si,int ei,int dp[][]){
        if(si+1 == ei) // for single left matrix cost of multiplication is 0
            return dp[si][ei] = 0;

        if(dp[si][ei] != -1)
            return dp[si][ei];
        
        int minCost = (int)1e9;
        for(int cut = si+1; cut < ei; cut++){
            int leftAns = mcm_memo1(arr,si,cut,dp);
            int rightAns = mcm_memo1(arr,cut,ei,dp);

            // q = arr[cut]
            int myAns = leftAns+ (arr[si]*(5*arr[cut]+8*(arr[cut]-1))*arr[ei]) + rightAns;
            minCost = Math.min(minCost,myAns);
        }

        return dp[si][ei] = minCost;
    }

 // https://www.geeksforgeeks.org/printing-brackets-matrix-chain-multiplication-problem/

    public static class pair{
        int minCost = (int)1e9;
        String minCostExp = "";

        pair(){

        }

        pair(int minCost,String minCostExp){
            this.minCost = minCost;
            this.minCostExp = minCostExp;
        }
    }

    public static String matrixChainOrder(int p[], int n){
        pair ans = mcm(p,n);
        return ans.minCostExp;
    }

    public static pair mcm(int p[],int n){
        pair dp[][] = new pair[n][n];

        pair ans = mcmBracket(p,0,n-1,dp);
        return ans;
    }

    public static pair mcmBracket(int p[],int si,int ei,pair dp[][]){
        if(si+1 == ei){
            char alpha = (char)(si+'A');
            return dp[si][ei] = new pair(0,alpha+"");
        }

        if(dp[si][ei] != null)
            return dp[si][ei];

        pair myAns = new pair();

        for(int cut=si+1;cut<ei;cut++){
            pair leftAns = mcmBracket(p,si,cut,dp);
            pair rightAns = mcmBracket(p,cut,ei,dp);

            int minCost = leftAns.minCost+(p[si]*p[cut]*p[ei])+rightAns.minCost;

            if(myAns.minCost > minCost){
                myAns.minCost = minCost;
                myAns.minCostExp = "("+leftAns.minCostExp+")"+"("+rightAns.minCostExp+")";
            }
        }

        return dp[si][ei] = myAns;
    }


// https://www.geeksforgeeks.org/minimum-maximum-values-expression/

// Faith: - hr cut point pe divide hokar apni min and max values return krdo   
    public static class minMaxPair{
        int minValue = (int)1e9;
        int maxValue = -(int)1e9;

        String minExp = "";
        String maxExp = "";

        minMaxPair(){

        }

        minMaxPair(int minValue,int maxValue){
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        
        minMaxPair(int minValue,int maxValue,String minExp,String maxExp){
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.minExp = minExp;
            this.maxExp = maxExp;
        }
    }


    public static int evaluate(int a,int b,char ch){
        if(ch == '+')
            return a+b;
        else
            return a*b;
    }

// here all the elements are positive and single digit else would need an array for the indices of opearators ,constraint(else we would have to do multiple comparisons)

    public static minMaxPair minMaxEvaluation(String str,int si,int ei,minMaxPair dp[][]){
        if(si == ei){
            int val = str.charAt(si) - '0';
            return dp[si][ei] = new minMaxPair(val,val,val+"",val+"");
        }

        if(dp[si][ei] != null)
            return dp[si][ei];

        minMaxPair myAns = new minMaxPair();
        for(int cut = si+1; cut < ei; cut = cut+2){
            minMaxPair leftAns = minMaxEvaluation(str,si,cut-1,dp);
            minMaxPair rightAns = minMaxEvaluation(str,cut+1,ei,dp);

// for both * & +, minValue = minValueLeft (operand) minValueRight
            int minValue = evaluate(leftAns.minValue,rightAns.minValue,str.charAt(cut));
            int maxValue = evaluate(leftAns.maxValue,rightAns.maxValue,str.charAt(cut));

            // myAns.minValue = Math.min(myAns.minValue,minValue);
            // myAns.maxValue = Math.max(myAns.maxValue,maxValue);

            if(myAns.minValue > minValue){
                myAns.minValue = minValue;
                myAns.minExp = "("+leftAns.minExp+" "+ str.charAt(cut)+" "+rightAns.minExp+")";
            }
            if(myAns.maxValue < maxValue){
                myAns.maxValue = maxValue;
                myAns.maxExp = "("+leftAns.maxExp+" "+ str.charAt(cut)+" "+rightAns.maxExp+")";
            }
        }

        return dp[si][ei] = myAns;
    }


// Leetcode 132. Palindrome Partitioning - II

// Faith:- Start to iterate from left to right and always make a valid cut at all possible palindromic substrings(which we will check in O(1) by pre-processing
    
    public int minCut(String s) {
        // pre-processing for boolean palindrome substring check in O(1)
        
        int n = s.length();
        boolean isPalindrome[][] = new boolean[n][n];
        
        for(int gap = 0;gap < n;gap++){
            for(int i = 0,j = gap;i < n && j < n;i++,j++){
                if(gap == 0)
                    isPalindrome[i][j] = true;
                else if(gap == 1)
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j));
                else
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j))?isPalindrome[i+1][j-1]:false;
            }
        }
        
        int dp[] = new int[n];
        Arrays.fill(dp,-1);
        
        int ans = minPartitions(s,0,isPalindrome,dp);
        return ans;
    }
    
    int minPartitions(String s,int si,boolean isPalindrome[][],int dp[]){
        // base case for avoiding the corner cases as mentioned in notes
        if(isPalindrome[si][s.length()-1])
            return dp[si] = 0;
        
        if(dp[si] != -1)
            return dp[si];
        
        int minCuts = (int)1e9;
        for (int cut = si; cut < s.length(); cut++) {
            if (isPalindrome[si][cut]) {
                minCuts = Math.min(minCuts, minPartitions(s, cut + 1, isPalindrome, dp) + 1);
            }
        }
        
        return dp[si] = minCuts;
    }


// https://practice.geeksforgeeks.org/problems/boolean-parenthesization5610/1

/*
Given a boolean expression S of length N with following symbols.
Symbols
    'T' ---> true
    'F' ---> false
and following operators filled between symbols
Operators
    &   ---> boolean AND
    |   ---> boolean OR
    ^   ---> boolean XOR
Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.

Example 1:

Input: N = 7
S = T|T&F^T
Output: 4
Explaination: The expression evaluates 
to true in 4 ways ((T|T)&(F^T)), 
(T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T)).

*/

    public static class pair{
        int trueWays = 0;
        int falseWays = 0;
        
        pair(){
            
        }
        
        pair(int trueWays,int falseWays){
            this.trueWays = trueWays;
            this.falseWays = falseWays;
        }
    }
    
    public static pair evaluate(pair left,pair right,char operator) {
        
        int mod = 1003;
        int TotalWays = ((left.trueWays + left.falseWays) % mod * (right.trueWays + right.falseWays) % mod) % mod;

        pair ans = new pair(0, 0);
        if (operator == '&') {
            ans.trueWays = (left.trueWays * right.trueWays) % mod;
            ans.falseWays = (TotalWays - ans.trueWays + mod) % mod;
        } else if (operator == '|') {
            ans.falseWays = (left.falseWays * right.falseWays) % mod;
            ans.trueWays = (TotalWays - ans.falseWays + mod) % mod;
        } else {
            ans.trueWays = (left.falseWays * right.trueWays) % mod + (left.trueWays * right.falseWays) % mod;
            ans.falseWays = (TotalWays - ans.trueWays + mod) % mod;
        }

        return ans;
    }
    
    public static pair parenthesize(String s,int si,int ei,pair dp[][]){
        if (si == ei) {
            char ch = s.charAt(si);
            if(ch == 'T')
                return dp[si][ei] = new pair(1,0);
            else
                return dp[si][ei] = new pair(0,1);
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pair myAns = new pair(0, 0);
        for (int cut = si + 1; cut < ei; cut += 2) {
            char op = s.charAt(cut);
            pair leftAns = parenthesize(s,si,cut-1,dp);
            pair rightAns = parenthesize(s,cut+1,ei,dp);

            pair recAns = evaluate(leftAns,rightAns,op);
            myAns.trueWays = (myAns.trueWays + recAns.trueWays) % 1003;
            myAns.falseWays = (myAns.falseWays + recAns.falseWays) % 1003;
        }

        return dp[si][ei] = myAns;
    }
    
    static int countWays(int N, String S){
        pair dp[][] = new pair[N][N];
        pair ans = parenthesize(S,0,N - 1,dp);

        return ans.trueWays;
    }

    public static void minMaxEvalutaionAns() {
        String str = "1+2*3+4*5";
        int n = str.length();
        minMaxPair [][] dp = new minMaxPair[n][n];

        minMaxPair ans = minMaxEvaluation(str, 0, n - 1, dp);
        System.out.println("Min Value: " + ans.minValue + "\nMin Expression: " + ans.minExp);
        System.out.println("Max Value: " + ans.maxValue + "\nMax Expression: " + ans.maxExp);

    }

// Leetcode 312. Burst Balloons
/*
You are given n balloons, indexed from 0 to n - 1. 
Each balloon is painted with a number on it represented by an array nums. 
You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. 
If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

Example:
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
*/

    public int burst(int arr[],int si,int ei,int dp[][]){
        
        if(dp[si][ei] != -1)
            return dp[si][ei];
        
        int leftVal = (si-1 == -1)?1:arr[si-1];
        int rightVal = (ei+1 == arr.length)?1:arr[ei+1];
        
        int maxCost = -(int)1e9;
        for(int cut = si;cut <= ei;cut++){
            int rightAns = (cut == ei)?0:burst(arr,cut+1,ei,dp); // handled rightmost index out of bound case
            int leftAns = (cut == si)?0:burst(arr,si,cut-1,dp); // handled left most index out of bound case
           
            
            int myAns = leftAns+leftVal*arr[cut]*rightVal+rightAns;
            maxCost = Math.max(maxCost,myAns);
        }
        
        return dp[si][ei] = maxCost;    
    }
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n][n];
        
        for(int d[]: dp)
            Arrays.fill(d,-1);
        
        int ans = burst(nums,0,n-1,dp);
        return ans;
    }

    public static void MCM(){

        int[] arr = {3,3};
        int n = arr.length;
        // int[][] dp = new int[n][n];
        // int  ans = MCM_DP_String(arr,0,n-1,dp);
        // for(int d[]: dp)
        //     Arrays.fill(d,-1);

        // int ans = mcm_memo1(arr,0,n-1,dp);
        //int ans=MCM_DP_String(arr,0,n-1,dp);

        pair dp[][] = new pair[n][n];

        String ans1 = matrixChainOrder(arr,n);
        System.out.println(ans1);

        // System.out.println(ans);
    }

    public static void main(String[] args){
        // minMaxEvalutaionAns();
        MCM();
    }

// Boolean Parenthesization




}