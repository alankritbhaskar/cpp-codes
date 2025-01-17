import java.util.*;

public class target_type{

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

// Coin change( Permutations ):-

/*

For example, if the coins are {2,3,5} and the desired sum is 9, there are 8 ways:

2 + 2 + 5
2 + 5 + 2
5 + 2 + 2
3 + 3 + 3
2 + 2 + 2 + 3
2 + 2 + 3 + 2
2 + 3 + 2 + 2
3 + 2 + 2 + 2

*/

// 2D Approach:-

public static int coinChangePermutations2D(int coins[],int n,int tar,int dp[][]){

    if(tar == 0)
    return dp[n][tar]=1;
    

    if(dp[n][tar]!=-1)
    return dp[n][tar];

    int ways=0;

    for(int coin: coins){
    if(tar-coin>=0)
    ways+=coinChangePermutations2D(coins,n,tar-coin,dp);
    }

    return dp[n][tar]=ways;
}

// 1D Approach(better):-

 public static int coinChangePermutations1D(int coins[],int tar,int dp[]){

 if(tar == 0)
 return dp[tar]=1;

 if(dp[tar]!=-1)
 return dp[tar];

 int ways=0;

 for(int coin: coins){
     if(tar-coin>=0)
     ways+=coinChangePermutations1D(coins,tar-coin,dp);
 }
 return dp[tar]=ways;
 }

 public static int coinChangePermutations1D_DP(int coins[],int Tar,int dp[]){

     for(int tar=0;tar<=Tar;tar++){
    
       if(tar==0)
       {
           dp[tar]=1;
           continue;
       }

    int ways=0;

    for(int coin: coins){
      if(tar-coin>=0)
       ways+=coinChangePermutations1D(coins,tar-coin,dp);
    }
    dp[tar]=ways;
     }
     return dp[Tar];
 }

 public static int coinChangePermutationDP(int[] arr,int Tar,int[] dp){
        dp[0] = 1;
        for(int tar = 1; tar <= Tar; tar++){
            for(int ele : arr){
                if(tar - ele >= 0){
                    dp[tar] += dp[tar-ele];
                }
            }            
        }

        return dp[Tar];
    }

// Coin Change(Combination):-

/*

For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.

*/

 public static int coinChangeCombinationDP(int[] arr,int Tar,int[] dp){
        dp[0] = 1;

         for(int ele : arr){
            for(int tar = 1; tar <= Tar; tar++){
                    dp[tar] += dp[tar-ele];
            }            
        }
        return dp[Tar];
    }

// https://practice.geeksforgeeks.org/problems/coin-change2448/1#
// repetition allowed

     public long coinChangeDP(int[] arr,int Tar,long[] dp1){
        dp1[0] = 1;

         for(int ele : arr){
            for(int tar = 1; tar <= Tar; tar++){
                    if(tar-ele>=0)
                    dp1[tar] += dp1[tar-ele];
            }            
        }
        return dp1[Tar];
    }

// coin change combination 2D

public long count(int s[], int m, int n) 
    { 
        long dp[][] = new long[m+1][n+1];
        
        for(long d[] : dp)
            Arrays.fill(d,-1);
            
        long ans = coinChange(s,0,n,dp);
        return ans;
    } 
    
    public long coinChange(int s[],int idx,int tar,long dp[][]){
        if(tar == 0)
            return dp[idx][tar] = 1;
            
        if(dp[idx][tar] != -1)
            return dp[idx][tar];
        
        long ways = 0;
        for(int i=idx;i<s.length;i++){
            if(tar-s[i]>=0)
                ways += coinChange(s,i,tar-s[i],dp);
        }
        
        return dp[idx][tar] = ways;
    }

// Leetcode 377:- Combination Sum-IV

 //Don't confuse with the question name it is asking about all the permutations
    
    public int combinationSum4(int[] nums, int target) {
        int dp[]=new int[target+1];
        Arrays.fill(dp,-1);
        int ans=coinChangePermutations1D(nums,target,dp);
        return ans;
    }
    
    public int coinChangePermutations1D(int coins[],int tar,int dp[]){

     if(tar==0)
       return dp[tar]=1;

     if(dp[tar]!=-1)
       return dp[tar];

     int ways=0;

     for(int coin: coins){
       if(tar-coin>=0)
       ways+=coinChangePermutations1D(coins,tar-coin,dp);
     }
     return dp[tar]=ways;
   }

// https://practice.geeksforgeeks.org/problems/reach-a-given-score/0#

int reachScore(vector<int> &scores,int n,vector<int> &dp){
 dp[0]=1;
 for(int score: scores){
     for(int i=score;i<=n;i++){
        dp[i]+=dp[i-score];
     }
 }
 return dp[n];
    
}
int reachScoreSolve(int n){
    vector<int> dp(n+1,0);
    vector<int> scores({3,5,10});
    int ans=reachScore(scores,n,dp);
    return ans;
}

// https://practice.geeksforgeeks.org/problems/count-ways-to-nth-stairorder-does-not-matter5639/1

    public long nthStair(int n)
    {
    
    int jumps[]={1,2};
    int dp[]=new int[n+1];
    long ans=countCombinations(jumps,n,dp);
    return ans;
    }
     public static long countCombinations(int[] arr,int Tar,int[] dp){
        dp[0] = 1;
        for(int ele : arr){
        for(int tar = ele; tar <= Tar; tar++){
                    dp[tar] += dp[tar-ele];
            }            
        }

        return dp[Tar];
    }

// Leetcode 322:- Coin change(min. no. of coins combination)

public int coinChange(int[] coins, int amount) {
        
        int dp[]=new int[amount+1];
        Arrays.fill(dp,-1);
        
        int ans=minCoinChangeDP(coins,amount,dp);
        return ans==(int)1e9?-1:ans;
    }
    
    public int minCoinChange(int coins[],int amt,int dp[]){
        
        if(amt == 0)
            return dp[amt]=0;
        
        if(dp[amt]!=-1)
            return dp[amt];
        
        // Dhyan dene ki baat hai ki yaha min ko 1e9 se initialize kiya hai not with INT_MAX bcoz us case mein +1 krne pe integer overflow hojayega
        
        // Agar aisa nhi krna chahte hai to...
        
        //int val = minCoinChange(coins,amt-coin,dp);
        //if(val != INT_MAX && val+1 < min_)
        //min_ = val+1;
        
        int min_ = (int)1e9;
        for(int coin: coins){
            if(amt-coin >= 0)
                min_ = Math.min(min_,minCoinChange(coins,amt-coin,dp)+1);
        }
        return dp[amt]=min_;
    }
    
    public int minCoinChangeDP(int coins[],int Amt,int dp[]){
        
        for(int amt=0;amt<=Amt;amt++){
        
        if(amt==0)
        {dp[amt]=0;
         continue;
        }
        
        int min_=(int)1e9;
        for(int coin: coins){
            if(amt-coin>=0)
                min_=Math.min(min_,dp[amt-coin]+1);
        }
            
        dp[amt]=min_;
    }
    return dp[Amt];
}

// https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/

public static int noOfWays(int coeff[],int Rhs){

int dp[] = new int[Rhs+1];
for(int tar=0;tar<=Rhs;tar++){
    for(int ele: coeff){
        if(tar-ele >= 0)
        dp[tar] += dp[tar-ele];
    }
}

return dp[Rhs];
}


//https://www.geeksforgeeks.org/subset-sum-problem-dp-25/  i.e. only addition allowed

    public static int targetSum(int[] arr,int idx,int tar,int[][] dp){
        if(tar == 0 || idx == arr.length){
            return dp[idx][tar] = tar == 0 ? 1 : 0;
        }

        if(dp[idx][tar] != -1) return dp[idx][tar];

        int count = 0;
        if(tar - arr[idx] >= 0)
          count += targetSum(arr,idx+1,tar - arr[idx], dp);
        count += targetSum(arr,idx+1,tar, dp);

        return dp[idx][tar] = count;
    }

    public static int targetSumDP(int[] arr,int Idx,int Tar,int[][] dp){
        for(int idx = arr.length;idx >= 0;idx--){
            for(int tar = 0;tar<=Tar;tar++){
                if(tar == 0 || idx == arr.length){
                    dp[idx][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }
                
                int count = 0;
                if(tar - arr[idx] >= 0)
                   count += dp[idx + 1][tar-arr[idx]];
                count += dp[idx + 1][tar];
               
                dp[idx][tar] = count;
            }
        }

        return dp[Idx][Tar];
    }


    public static int targetSum2(int[] arr,int n,int tar,int[][] dp){
        if(tar == 0 || n == 0){
            return dp[n][tar] = (tar == 0) ? 1 : 0;
        }

        if(dp[n][tar] != -1) return dp[n][tar];

        int count = 0;
        if(tar - arr[n - 1] >= 0)
          count += targetSum2(arr,n - 1,tar - arr[n - 1], dp);
        count += targetSum2(arr,n - 1,tar, dp);

        return dp[n][tar] = count;
    }

    public static int targetSumDP2(int[] arr,int N,int Tar,int[][] dp){
        for(int n = 0;n<=N;n++){
            for(int tar = 0;tar<=Tar;tar++){
                if(tar == 0 || n == 0){
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }
                
                int count = 0;
                if(tar - arr[n - 1] >= 0)
                   count += dp[n-1][tar-arr[n-1]];
                count += dp[n-1][tar];
               
                dp[n][tar] = count;
            }
        }

        return dp[N][Tar];
    }

// For single target sum path (a way to reverse engineer from dp)

    public static boolean targetSumPath(int[] arr,int n,int tar,int[][] dp,String psf){
        if(tar == 0 || n == 0){
           if(tar == 0){
            System.out.println(psf);
            return true;
           }
           return false;
        }

        boolean res = false;
        if(tar - arr[n - 1] >= 0 && dp[n-1][tar - arr[n-1]] > 0)
          res = res || targetSumPath(arr,n - 1,tar - arr[n - 1], dp, psf + arr[n-1] + ",");
        if(dp[n-1][tar] > 0) res = res || targetSumPath(arr,n - 1,tar, dp,psf);

        return res;
    }

// For all target sum paths

    public static int targetSumPath(int[] arr,int n,int tar,int[][] dp,String psf){
        if(tar == 0 || n == 0){
           if(tar == 0){
            System.out.println(psf);
            return 1;
           }
           return 0;
        }

        int res = 0;
        if(tar - arr[n - 1] >= 0 && dp[n-1][tar - arr[n-1]] > 0)
          res += targetSumPath(arr,n - 1,tar - arr[n - 1], dp, psf + arr[n-1] + ",");
        if(dp[n-1][tar] > 0) 
        res += targetSumPath(arr,n - 1,tar, dp,psf);

        return res;
    }

// Leetcode 416:- Partition Equal Subset Sum

 public boolean canPartition(int[] arr) {
        int N = arr.length;
        if(N==0) return false;

        int sum = 0;
        for(int ele : arr) sum+=ele;
        if(sum % 2 != 0) return false;

        int Tar = sum / 2;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for(int n = 0;n<=N;n++){
            for(int tar = 0;tar<=Tar;tar++){
                if(tar == 0 || n == 0){
                    dp[n][tar] = (tar == 0) ? true : false;
                    continue;
                }
                
                int count = 0;
                if(tar - arr[n - 1] >= 0)
                   dp[n][tar] = dp[n][tar] || dp[n-1][tar-arr[n-1]];
                dp[n][tar] = dp[n][tar] ||  dp[n-1][tar];               
            }
        }
        System.out.print(dp[N][Tar]);
        return dp[N][Tar];
    }

// Leetcode 494. Target Sum

    // Recursion
        public int target(int arr[],int n,int tar){
        if(n == 0){
            return (tar == 0)?1:0; 
        }

        int count = 0;
        count += target(arr,n-1,tar-arr[n-1]); // as +ve
        count += target(arr,n-1,tar-(-arr[n-1])); // as -ve

        return count;
    }
    
    // Memoization
    
        public int target1(int arr[],int n,int tar,int sum,int dp[][]){
        if(n == 0){
            return (tar == sum)?1:0; // 0 -> sum
        }
        
        if(dp[n][tar] != -1)
            return dp[n][tar];
            
        int count = 0;
        if(tar-arr[n-1] >= 0)
        count += target1(arr,n-1,tar-arr[n-1],sum,dp); // as +ve
        if(tar+arr[n-1] <= 2*sum)
        count += target1(arr,n-1,tar-(-arr[n-1]),sum,dp); // as -ve

        return dp[n][tar] = count;
    }

    public int findTargetSumWays(int nums[],int S){
        
        int sum = 0;
        for(int ele : nums)
            sum += ele;
        
        if(sum < S || S < -sum)
            return 0;
        
        int n = nums.length;
        int dp[][] = new int[n+1][2*sum+1];
        for(int d[] :dp)
            Arrays.fill(d,-1);
        
        int ans = target1(nums,nums.length,S+sum,sum,dp);
        return ans;
    }

//https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/

    static int knapSack(int W, int wt[], int val[], int n) 
    { 
       if(n == 0 || W == 0)
        return 0;
        
       int dp[][] = new int[n+1][W+1];
       
       for(int d[]: dp)
       Arrays.fill(d,-1);
       
       int ans = knap(wt,val,0,W,dp);
       return ans;
    }
    
// Faith:- once i will consider the no. to be a part of my subbset and not to be part and then choose accordingly the best answer 

   static int knap(int wt[],int val[],int idx,int tar,int dp[][]){
        if(idx == wt.length || tar == 0)
            return dp[idx][tar] = 0;
            
        if(dp[idx][tar] != -1)
            return dp[idx][tar];
            
        int maxP = -(int)1e9;
        int max1 = -(int)1e9;
        int max2 = -(int)1e9;
        
        if(tar-wt[idx] >= 0)
            max1 = knap(wt,val,idx+1,tar-wt[idx],dp)+val[idx];// include after checking
        max2 = knap(wt,val,idx+1,tar,dp);// exclude the element no need to check
        maxP = Math.max(max1,max2);
        
        return dp[idx][tar] = maxP;
    }

// ///////////////////////////////////////////////////////////////////////////////////////

    public static int knapsack01(int[] weight,int[] value,int n,int tar,int[][] dp){
        
        if(n == 0 || tar == 0){
            return dp[n][tar] = 0;
        }

        if(dp[n][tar] != -1) return dp[n][tar];

        int maxValue = 0;
        if(tar - weight[n - 1] >= 0)
           maxValue = knapsack01(weight,value,n - 1,tar - weight[n - 1],dp) + value[n - 1];
        maxValue = Math.max(maxValue, knapsack01(weight,value,n - 1,tar,dp));
        
        return dp[n][tar] = maxValue;
    }
    
    static int knapSack(int W, int wt[], int val[], int n) 
    { 
        int dp[][]=new int[n+1][W+1];
        for(int d[]: dp)
        Arrays.fill(d,-1);
        int ans=knapsack01(wt,val,n,W,dp);
        return ans;
        
    }



// https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/

     static int knapSack(int N, int W, int val[], int wt[])
    {
        int dp[][] = new int[N+1][W+1];
        for(int d[]: dp)
            Arrays.fill(d,-1);
            
        // int ans = knap(wt,val,0,W,dp);
        int ans = knap1(wt,val,W);
        return ans;
    }
    
    static int knap(int wt[],int val[],int idx,int tar,int dp[][]){
        if(idx == wt.length || tar == 0)
            return dp[idx][tar] = 0;
            
        if(dp[idx][tar] != -1)
            return dp[idx][tar];
            
        int maxP = -(int)1e8;
        int max1 = -(int)1e8;
        int max2 = -(int)1e8;
        
        if(tar-wt[idx] >= 0)
            max1 = knap(wt,val,idx,tar-wt[idx],dp)+val[idx];// include(duplicacy allowed so passed same index)
        max2 = knap(wt,val,idx+1,tar,dp); // exclude
        
        maxP =  Math.max(max1,max2);
        return dp[idx][tar] = maxP;
    }

// Like coin change change
 static int knap1(int[] wt,int[] val,int Tar){
        int[] dp = new int[Tar+1];
        
        for(int i = 0;i<wt.length;i++){   
           for(int tar = wt[i];tar <= Tar;tar++){
               dp[tar] = Math.max(dp[tar],dp[tar - wt[i]] + val[i]);
           }
        }

        return dp[Tar];
    }

// Leetcode 279. Perfect Squares

    int numSquares(int n) {
        vector<int> dp(n+1,-1);
        
        int ans = minPerfect(n,dp);
        return ans;
    }
    
    int minPerfect(int n,vector<int> &dp){
        if(n == 0)
            return dp[n] = 0;
        
        if(dp[n] != -1)
            return dp[n];
        
        int minSq = (int)1e9;
        for(int i = 1;i*i <= n;i++){
            int count = 1+minPerfect(n-i*i,dp);
            minSq = min(minSq,count);
        }
        return dp[n] = minSq;
    }

// 698 Partition in k equal subsets

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int maxElement = -(int)1e8;
        
        for(int ele: nums){
            sum += ele;
            maxElement = Math.max(ele,maxElement);
        }
        
        // all elements are positive
        if(sum%k != 0 || maxElement > (sum/k))
            return false;
        
        boolean vis[] = new boolean[n];// initally false
        int tar = (sum/k);
        boolean res = kSubset(nums,tar,0,k,0,vis);
        return res;       
    }
    
    public boolean kSubset(int arr[],int tar,int currSum,int k,int idx,boolean vis[]){
        if(k == 0)
            return true;
        if(currSum == tar)
            return kSubset(arr,tar,0,k-1,0,vis);// again search for tar in k-1 remaining subsets
        
        boolean res = false;
        for(int i=idx;i<arr.length;i++){
            if(!vis[i]){
                vis[i] = true;
                res = res || kSubset(arr,tar,currSum+arr[i],k,i+1,vis);
                vis[i] = false;
            }
        }
        
        return res;
    }

    public static int solve(){
        int ar[]={2,3,5};
        int tar=9;
        int dp[][]=new int[ar.length+1][tar+1];
        
        for(int d[]:dp)
        Arrays.fill(d,-1);
        int dpp[]=new int[tar+1];
        Arrays.fill(dpp,-1);
         int dppp[]=new int[tar+1];
         Arrays.fill(dppp,-1);
        int ans=coinChangePermutations2D(ar,3,tar,dp);
        int ans1=coinChangePermutations1D_DP(ar,tar,dpp);
        int ans2=coinChangePermutations1D(ar,tar,dppp);
        print2D(dp);
         System.out.println(ans);
        print(dpp);
         System.out.println(ans1);
         print(dppp);
          System.out.println(ans2);
      
        // targetSum();
        return ans;
    }




    public static void main(String[] args){
        solve();
    }
}
