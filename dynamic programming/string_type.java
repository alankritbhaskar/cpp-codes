import java.util.*;

public class string_type{

    public static void print(int[] arr){
        for(int ele: arr)
          System.out.print(ele + " "); 
        
        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] a: arr)
          print(a);

        System.out.println();
    }

    public static void palindromicSubstring(String str,boolean[][] dp){
        int n = str.length();
        for(int gap = 0;gap < n;gap++){
            for(int i=0,j=gap; j < n;i++,j++){
                if(gap == 0) dp[i][j] = true;
                else if(gap == 1) dp[i][j] = str.charAt(i) == str.charAt(j);
                else dp[i][j] = str.charAt(i) == str.charAt(j)  && dp[i+1][j-1];
            }
        }
    } 


    //Leetcode 647
    public int countSubstrings(String s) {
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0; 
        for(int gap = 0;gap < n;gap++){
            for(int i=0,j=gap; j < n;i++,j++){
                if(gap == 0) dp[i][j] = true;
                else if(gap == 1) dp[i][j] = str.charAt(i) == str.charAt(j);
                else dp[i][j] = str.charAt(i) == str.charAt(j)  && dp[i+1][j-1];
            
                if(dp[i][j]) count++; 
            }
        }
        return count;
    }

// Leetcode 005:- Longest palindromic substring

    public String longestPalindrome(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        
        int si = 0,ei = 0,length = 0; // starting index, ending index of longest palindromic susbtring.
        
        for(int gap = 0;gap < n;gap++){
            for(int i=0,j=gap; j < n;i++,j++){

                if(gap == 0)
                dp[i][j] = 1;
                else if(gap == 1 && str.charAt(i) == str.charAt(j)) 
                dp[i][j] = 2;
                else if(str.charAt(i) == str.charAt(j) && dp[i+1][j-1] > 0)
                dp[i][j] = dp[i+1][j-1] + 2;
            
                if(dp[i][j] > length){
                    length = dp[i][j];
                    si = i;
                    ei = j;
                } 
            }
        }

        return str.substring(si,ei+1);
    }

// Leetcode 516:- Longest Palindromic Subsequence

    public int longestPalindromeSubseq(String s) {
       int n=s.length();
       
       int dp[][]=new int[n][n];
       int ans=lpsDP(s,0,n-1,dp);
       return ans;
    }
    public int lpsDP(String s,int I,int J,int dp[][]){
        int n=s.length();
        
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;i<n && j<n;i++,j++){
        
        if(i==j)
        {dp[i][j]=(i==j)?1:0;
         continue;
        }
        
        
        int l=0;
        if(s.charAt(i)==s.charAt(j))
            l=dp[i+1][j-1]+2;
        else
            l=Math.max(dp[i+1][j],dp[i][j-1]);
        
        dp[i][j]=l;
            }
        }
        return dp[I][J];
    }
    public int lps(String s,int i,int j,int dp[][]){
        
        if(i>j || i==j)
            return dp[i][j]=(i==j)?1:0;
        
        if(dp[i][j]>0)
            return dp[i][j];
        
        int l=0;
        if(s.charAt(i)==s.charAt(j))
            l=lps(s,i+1,j-1,dp)+2;
        else
            l=Math.max(lps(s,i+1,j,dp),lps(s,i,j-1,dp));
        
        return dp[i][j]=l;
    }

// Leetcode 115:- Distinct Subsequences

    public int numDistinct(String s, String t) {
      int n=s.length();
      int m=t.length();
      int dp[][]=new int[n+1][m+1];
      
      for(int d[]: dp)
          Arrays.fill(d,-1);
        
      // int ans=distinctMemo(s,t,n,m,dp);
      int ans=distinctDP(s,t,n,m,dp);
      return ans;
    }
    
     public int distinctDP(String s,String t,int N,int M,int dp[][]){
        
        for(int n=0;n<=s.length();n++){
        
            for(int m=0;m<=t.length();m++){
        
        if(m == 0){
            dp[n][m] = 1;
            continue;
        }

        if(n < m){
            dp[n][m] = 0;
            continue;
        }
        
        
        int count=0,c1=0,c2=0;
        if(s.charAt(n-1)==t.charAt(m-1)){
            
        //count=fn(s,t,n-1,m-1,dp)+fn(s,t,n-1,m)
            
        c1=dp[n-1][m-1];//distinctMemo(s,t,n-1,m-1,dp);
        c2=dp[n-1][m];//distinctMemo(s,t,n-1,m,dp);
            
        count+=c1+c2;
        }
        else{
            
        //count=fn(s,t,n-1,m)
            
        c1=dp[n-1][m];//distinctMemo(s,t,n-1,m,dp);
        count+=c1;
    }
         dp[n][m]=count;
            }
        }
         return dp[N][M];
}
    
    public int distinctMemo(String s,String t,int n,int m,int dp[][]){
        
        if(m == 0){
            return dp[n][m] = 1;
        }

        if(n < m){
            return dp[n][m] = 0;
        }
        
            
        
        if(dp[n][m]!=-1)
            return dp[n][m];
        
        int count=0,c1=0,c2=0;
        if(s.charAt(n-1)==t.charAt(m-1)){
            
        //count=fn(s,t,n-1,m-1,dp)+fn(s,t,n-1,m)
            
        c1=distinctMemo(s,t,n-1,m-1,dp);
        c2=distinctMemo(s,t,n-1,m,dp);
            
        count+=c1+c2;
        }
        else{
            
        //count=fn(s,t,n-1,m)
            
        c1=distinctMemo(s,t,n-1,m,dp);
        count+=c1;
    }
        return dp[n][m]=count;
}

// GFG:- Count All Palindromic Subsequences..... https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1

    int countPS(String str)
    {
        int n=str.length();
        int dp[][]=new int[n][n];
        
        for(int d[]: dp)
        Arrays.fill(d,-1);
        
        int ans=countPalindromicSubMemo(0,n-1,dp,str);
        return ans;
    }
    
    
    int countPalindromicSubDP(int SI,int EI,int dp[][],String s){
        
        for(int gap=0;gap<s.length();gap++){
            for(int si=0,ei=gap;ei<s.length();si++,ei++){
                
        if(si==ei)
        {
            dp[si][ei]=1;
            continue;
        }
        
        else if(Math.abs(si-ei)==1)
        {
            if(s.charAt(si)==s.charAt(ei))
            {
                dp[si][ei]=3;
                continue;
            }
            else{
            dp[si][ei]=2;
            continue;
            }
        }
        
        
        int c=0;
        if(s.charAt(si)==s.charAt(ei)){
       
        // count= x+1+y+z-(x)=y+z+1 ...... here, when we check the count then we can see a total of x common terms
        
        // c+=countPalindromicSubMemo(si+1,ei-1,dp,s);
        c+=countPalindromicSubMemo(si,ei-1,dp,s);
        c+=countPalindromicSubMemo(si+1,ei,dp,s);
        c++;
        }
        else{
        // count= y+z-x ...... x terms are common in y and z calls
        
        c+=countPalindromicSubMemo(si,ei-1,dp,s);
        c+=countPalindromicSubMemo(si+1,ei,dp,s);
        c-=countPalindromicSubMemo(si+1,ei-1,dp,s);
        }
        
        dp[si][ei]=c;
         }
        }
        return dp[SI][EI];
    }
    
    int countPalindromicSubMemo(int si,int ei,int dp[][],String s){
        
        if(si==ei)
        return dp[si][ei]=1;
        
        else if(Math.abs(si-ei)==1)
        {
            if(s.charAt(si)==s.charAt(ei))
            return dp[si][ei]=3;
            else
            return dp[si][ei]=2;
        }
        
        if(dp[si][ei]!=-1)
        return dp[si][ei];
        
        int c=0;
        if(s.charAt(si)==s.charAt(ei)){
       
        // count= x+1+y+z-(x)=y+z+1 ...... here, when we check the count then we can see a total of x common terms
        
        // c+=countPalindromicSubMemo(si+1,ei-1,dp,s);
        c+=countPalindromicSubMemo(si,ei-1,dp,s);
        c+=countPalindromicSubMemo(si+1,ei,dp,s);
        c++;
        }
        else{
        // count= y+z-x ...... x terms are common in y and z calls
        
        c+=countPalindromicSubMemo(si,ei-1,dp,s);
        c+=countPalindromicSubMemo(si+1,ei,dp,s);
        c-=countPalindromicSubMemo(si+1,ei-1,dp,s);
        }
        
        return dp[si][ei]=c;
    }
}







    public static void main(String args[]){


    }

}

