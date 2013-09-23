package local.CLFrankie;

import java.util.*;

public class Sudoky
{
 public static boolean finish;

 public static void solve(Board board)
 {
  finish = false;
  
  /*
   * Check current solution
   */
  if(board.isFinished())
  {
   finish = true;
   return;
  }


  /*
   * Prepare candidates
   */
  ArrayList<Candidate> candidates = prepareCandidates(board);

  for(int i = 0; i < candidates.size(); i++)
  {
   placeCandidate(board, candidates.get(i));
   solve(board);
   if(finish)
    break;
   unplaceCandidate(board, candidates.get(i));
  }
 }

 public static void printBoard(Board board)
 {
  for(int i = 0; i < board.mDimension; i++)
  {
   if(i % 3 == 0)
     System.out.println("--------------------------------");
   for(int j = 0; j < board.mDimension; j++)
   {
    if(j % 3 == 0)
      System.out.print(" | ");
    System.out.print(board.mData[i][j] + " ");
   }
   System.out.println("");
  }
  System.out.println("--------------------------------");
 }

 public static void placeCandidate(Board board, Candidate candidate)
 {
  board.place(candidate.mX, candidate.mY, candidate.mVal);
 }

 public static void unplaceCandidate(Board board, Candidate candidate)
 {
  board.unplace(candidate.mX, candidate.mY);
 }
 

 /*
  * Random pick
  * Look local
  */
 public static ArrayList<Candidate> prepareCandidates(Board board)
 {
  return pick(board);
 }

 
 /*
  * Picking method
  */
 public static ArrayList<Candidate> pick(Board board)
 {
  ArrayList<Integer[]> points = board.getEmpties();

  Random rand = new Random();
  Integer[] picked = points.get(rand.nextInt(points.size()));
  ArrayList<Candidate> candidates = generate(board, picked[0], picked[1]);
  ArrayList<Candidate> temp = null;

  for(int i = 1; i < points.size(); i++)
  {
   temp = generate(board, points.get(i)[0], points.get(i)[1]);
   if(candidates.size() > temp.size())
   {
    picked = points.get(i);
    candidates = temp;
   }
  }

  return candidates;
 }


 /*
   * Generate candidates
  */
 public static ArrayList<Candidate> generate(Board board, int x, int y)
 {
  int[] row = board.getRow(x, y);
   InsertionSort.sort(row);

  int[] col = board.getCol(x, y);
   InsertionSort.sort(col);

  int[] sec = to1Dim(board.getSec(x, y));
   InsertionSort.sort(sec);

  ArrayList<Integer> rowAvai = new ArrayList<Integer>();
  for(int i = 1; i <= row.length; i++)
   if(BinarySearch.search(row, i) == -1)
    rowAvai.add((Integer)i);

  ArrayList<Integer> colAvai = new ArrayList<Integer>();
  for(int i = 1; i <= col.length; i++)
   if(BinarySearch.search(col, i) == -1)
    colAvai.add((Integer)i);

  ArrayList<Integer> secAvai = new ArrayList<Integer>();
  for(int i = 1; i <= sec.length; i++)
   if(BinarySearch.search(sec, i) == -1)
    secAvai.add((Integer)i);

  ArrayList<Candidate> candidates = new ArrayList<Candidate>();
  for(int i = 1; i <= board.mDimension; i++)
   if(BinarySearch.search(rowAvai, i) != -1 && BinarySearch.search(colAvai, i) != -1 && BinarySearch.search(secAvai, i) != -1)
    candidates.add(new Candidate(x, y, i));


  return candidates;
 }

 public static int[] to1Dim(int[][] a)
 {
  if(a.length <= 0)
   return null;
  
  int[] ret = new int[a.length * a[0].length];
  int counter = 0;
  
  for(int i = 0; i < a.length; i++)
   for(int j = 0; j < a[i].length; j++)
    ret[counter++] = a[i][j];
  return ret;
 }
}
