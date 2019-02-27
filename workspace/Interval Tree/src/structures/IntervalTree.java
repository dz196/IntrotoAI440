package structures;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Encapsulates an interval tree.
 * 
 * @author runb-cs112
 */
public class IntervalTree {
	
	/**
	 * The root of the interval tree
	 */
	IntervalTreeNode root;
	
	/**
	 * Constructs entire interval tree from set of input intervals. Constructing the tree
	 * means building the interval tree structure and mapping the intervals to the nodes.
	 * 
	 * @param intervals Array list of intervals for which the tree is constructed
	 */
	public IntervalTree(ArrayList<Interval> intervals) {
		
		// make a copy of intervals to use for right sorting
		ArrayList<Interval> intervalsRight = new ArrayList<Interval>(intervals.size());
		for (Interval iv : intervals) {
			intervalsRight.add(iv);
		}
		
		// rename input intervals for left sorting
		ArrayList<Interval> intervalsLeft = intervals;
		
		// sort intervals on left and right end points
		sortIntervals(intervalsLeft, 'l');
		sortIntervals(intervalsRight,'r');
		System.out.println(Arrays.toString(intervalsLeft.toArray()));
		System.out.println(Arrays.toString(intervalsRight.toArray()));
		// get sorted list of end points without duplicates
		ArrayList<Integer> sortedEndPoints = 
							getSortedEndPoints(intervalsLeft, intervalsRight);
		
		// build the tree nodes
		root = buildTreeNodes(sortedEndPoints);
		
		// map intervals to the tree nodes
		mapIntervalsToTree(intervalsLeft, intervalsRight);
	}
	
	/**
	 * Returns the root of this interval tree.
	 * 
	 * @return Root of interval tree.
	 */
	public IntervalTreeNode getRoot() {
		return root;
	}
	
	/**
	 * Sorts a set of intervals in place, according to left or right endpoints.  
	 * At the end of the method, the parameter array list is a sorted list. 
	 * 
	 * @param intervals Array list of intervals to be sorted.
	 * @param lr If 'l', then sort is on left endpoints; if 'r', sort is on right endpoints
	 */
	public static void sortIntervals(ArrayList<Interval> intervals, char lr) {
		// COMPLETE THIS METHOD
		//ACCOUNT FOR DUPLICATE INTERVALS WITH DIFFERENT DESCRIPTIONS
		int comma = 0;
		int leftNum = 0;
		int rightNum = 0;
		String left = "";
		String right = "";
		String s = "";
		int [] intArray = new int[intervals.size()];
		int [] leftNums = new int[intervals.size()];
		int [] rightNums = new int[intervals.size()];
		ArrayList<Interval> tmp = new ArrayList<Interval>();
		if (lr == 'l') // Sort Left intervals
		{
			for (int i = 0; i < intervals.size(); i++)
			{
				s = intervals.get(i).toString(); // Initialize each object to a String
				for (int j = 0; j < s.length(); j++)
				{
					if (s.charAt(j) == ',') // Find the index of the comma
					{
						comma = j;
						left = s.substring(1,j); // 0th index is always the first bracket. String of left interval
						leftNum = Integer.parseInt(left); //String to int
						intArray[i] = leftNum; // Add to intArray
						leftNums[i] = leftNum; // Add to array of leftNums
					}
					if (s.charAt(j) == ']') // Find index of closing bracket
					{
						right = s.substring(comma+1, j); // String of right interval
						rightNum = Integer.parseInt(right); // String to int
						rightNums[i] = rightNum; // Add to array of rightNums
					}
				}
			}
			//System.out.println("LeftNums: " + Arrays.toString(intArray));
			Arrays.sort(intArray); // Sort from lowest to highest
			//System.out.println("Sorted Values: " + Arrays.toString(intArray));
			//Compare values in both arrays
			for (int i = 0; i < intArray.length; i++)
			{
				for (int j = 0; j < leftNums.length; j++)
				{
					if (intArray[i] == leftNums[j]) //Look for a match
					{
						tmp.add(intervals.get(j));
						leftNums[j] = -1;
						break;
					}
				}
			}
			intervals.clear();
			intervals.addAll(tmp);
			//System.out.println("FINAL LEFT INTERVALS: " + Arrays.toString(intervals.toArray()));
			for (int i = 0; i < intervals.size(); i++)
			{
			System.out.println("LeftEndPoints: " + intervals.get(i).leftEndPoint);
			}
		}
		if (lr == 'r') // Sort Left intervals
		{
			for (int i = 0; i < intervals.size(); i++)
			{
				s = intervals.get(i).toString(); // Initialize each object to a String
				for (int j = 0; j < s.length(); j++)
				{
					if (s.charAt(j) == ',') // Find the index of the comma
					{
						comma = j;
						left = s.substring(1,j); // 0th index is always the first bracket. String of left interval
						leftNum = Integer.parseInt(left); //String to int
						leftNums[i] = leftNum; // Add to array of leftNums
					}
					if (s.charAt(j) == ']') // Find index of closing bracket
					{
						right = s.substring(comma+1, j); // String of right interval
						rightNum = Integer.parseInt(right); // String to int
						rightNums[i] = rightNum; // Add to array of rightNums
						intArray[i] = rightNum; // Add to intArray
					}
				}
			}
			//System.out.println("RightNums: " + Arrays.toString(intArray));
			Arrays.sort(intArray); // Sort from lowest to highest
			//System.out.println("Sorted Values: " + Arrays.toString(intArray));
			//Compare values in both arrays
			for (int i = 0; i < intArray.length; i++)
			{
				for (int j = 0; j < rightNums.length; j++)
				{
					if (intArray[i] == rightNums[j]) //Look for a match
					{
						tmp.add(intervals.get(j));
						/*Interval interval = new Interval(leftNums[j], rightNums[j], "");
						System.out.println("ObjInterval: " + interval);
						tmp.add(interval);*/ // Index of leftNums match indexes from intervals
						rightNums[j] = -1;
						break;
					}
				}
			}
			//intervals = tmp; WHY DOESNT THIS WORK
			intervals.clear();
			intervals.addAll(tmp);
			//System.out.println("FINAL RIGHT INTERVALS: " + Arrays.toString(intervals.toArray()));
			for (int i = 0; i < intervals.size(); i++)
			{
			System.out.println("RightEndPoints: " + intervals.get(i).rightEndPoint);
			}
		}
	}
	
	/**
	 * Given a set of intervals (left sorted and right sorted), extracts the left and right end points,
	 * and returns a sorted list of the combined end points without duplicates.
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 * @return Sorted array list of all endpoints without duplicates
	 */
	public static ArrayList<Integer> getSortedEndPoints(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		ArrayList<Integer> points = new ArrayList<Integer>();
		//System.out.println("LEFT INTERVALS: " + Arrays.toString(leftSortedIntervals.toArray()));
		//System.out.println("RIGHT INTERVALS: " + Arrays.toString(rightSortedIntervals.toArray()));
		int left = leftSortedIntervals.size();
		int right = rightSortedIntervals.size(); 
		int i = 0; 
		int j = 0;
		int last = 0;
		while (i < left && j < right)
		{
			//System.out.println("LEFT POINT: " + leftSortedIntervals.get(i).leftEndPoint);
			//System.out.println("RIGHT POINT: " + rightSortedIntervals.get(j).rightEndPoint);
			if (leftSortedIntervals.get(i).leftEndPoint < rightSortedIntervals.get(j).rightEndPoint)
			{
				if (last != leftSortedIntervals.get(i).leftEndPoint)
				{
					last = leftSortedIntervals.get(i).leftEndPoint;
					//System.out.println("Left Point Added: " + leftSortedIntervals.get(i).leftEndPoint);
					points.add(leftSortedIntervals.get(i).leftEndPoint);
				}
				i++;
			}
			else if (leftSortedIntervals.get(i).leftEndPoint > rightSortedIntervals.get(j).rightEndPoint)
			{
				if (last != rightSortedIntervals.get(j).rightEndPoint)
				{
				last = rightSortedIntervals.get(j).rightEndPoint;
				//System.out.println("Right Point Added: " + rightSortedIntervals.get(j).rightEndPoint);
				points.add(rightSortedIntervals.get(j).rightEndPoint);
				}
				j++;
			}
			else
			{
				if (last != rightSortedIntervals.get(j).rightEndPoint)
				{
				last = rightSortedIntervals.get(j).rightEndPoint;
				//System.out.println("Mutual Point Added: " + rightSortedIntervals.get(j).rightEndPoint);
				points.add(rightSortedIntervals.get(j).rightEndPoint);
				}
				i++;
				j++;
			}
		}
		while (i < left)
		{
			if (last != leftSortedIntervals.get(i).leftEndPoint)
			{
				last = leftSortedIntervals.get(i).leftEndPoint;
				//System.out.println("IValue: " + i);
				points.add(leftSortedIntervals.get(i).leftEndPoint);
			}
			i++;
		}
		while (j < right)
		{
			if (last != rightSortedIntervals.get(j).rightEndPoint)
			{
				last = rightSortedIntervals.get(j).rightEndPoint;
				//System.out.println("JValue: " + j);
				points.add(rightSortedIntervals.get(j).rightEndPoint);
			}
			j++;
		}
		System.out.println("POINTS: " + points);
		return points;
	}
	
	/**
	 * Builds the interval tree structure given a sorted array list of end points
	 * without duplicates.
	 * 
	 * @param endPoints Sorted array list of end points
	 * @return Root of the tree structure
	 */
	public static IntervalTreeNode buildTreeNodes(ArrayList<Integer> endPoints) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		//ADD CODE TO GET THE RIGHT MAX/MIN SPLITVALUES
		Queue<IntervalTreeNode> q = new Queue<IntervalTreeNode>();
		for (int i = 0; i < endPoints.size(); i++)
		{
			int splitValue = endPoints.get(i);
			IntervalTreeNode tmp = new IntervalTreeNode(splitValue, splitValue, splitValue);
			//System.out.println("Value: " + tmp.splitValue);
			q.enqueue(tmp);
		}
		boolean check = true;
		while (check)
		{
			if (q.size() == 1)
			{
				IntervalTreeNode root = q.dequeue();
				return root;
			}
			int temps =q.size();
			while (temps > 1)
			{
				//System.out.println("Temps: " + temps);
				IntervalTreeNode T1 = q.dequeue();
				IntervalTreeNode T2 = q.dequeue();
				float v1 = T1.maxSplitValue;
				float v2 = T2.minSplitValue;
				float minVal = T1.minSplitValue;
				float maxVal = T2.maxSplitValue;
				float x = (v1 + v2) / 2;
				IntervalTreeNode N = new IntervalTreeNode(x, minVal, maxVal);
				System.out.println("SplitValue: " + x + ",minSplitValue: " + minVal + ",maxSplitValue: " + maxVal);
				N.leftChild = T1;
				N.rightChild = T2;
				q.enqueue(N);
				temps -= 2;
			}
			if (temps == 1)
			{
				q.enqueue(q.dequeue());
			}
		}
		return null;
	}
	
	/**
	 * Maps a set of intervals to the nodes of this interval tree. 
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 */
	public void mapIntervalsToTree(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		// COMPLETE THIS METHOD
		
		IntervalTreeNode T = root;
		System.out.println("Root Value: " + T.splitValue);
		for (int i = 0; i < leftSortedIntervals.size(); i++)
		{
			//System.out.println(leftSortedIntervals.get(i));
			IntervalTreeNode tmp = search(T, leftSortedIntervals.get(i));
			Interval tmpInterval = leftSortedIntervals.get(i);
			if (tmp.leftIntervals == null)
			{
				tmp.leftIntervals = new ArrayList<Interval>();
			}
			tmp.leftIntervals.add(tmpInterval);
			System.out.println("NODE: " + tmp.splitValue + " Left Intervals: " + tmp.leftIntervals);
		}
		for (int i = 0; i < rightSortedIntervals.size(); i++)
		{
			//System.out.println(rightSortedIntervals.get(i));
			IntervalTreeNode tmp = search(T, rightSortedIntervals.get(i));
			Interval tmpInterval = rightSortedIntervals.get(i);
			if (tmp.rightIntervals == null)
			{
				tmp.rightIntervals = new ArrayList<Interval>();
			}
			tmp.rightIntervals.add(tmpInterval);
			System.out.println("NODE: " + tmp.splitValue + " Right Intervals: " + tmp.rightIntervals);
		}
	}
	private IntervalTreeNode search(IntervalTreeNode T , Interval interval)
	{
		float splitVal = T.splitValue;
		if (splitVal >= interval.leftEndPoint && splitVal <= interval.rightEndPoint)
		{
			//System.out.println("Highest Node: " + splitVal);
			return T;
		}
		else if (splitVal < interval.leftEndPoint)
			return search(T.rightChild, interval);
		else if (splitVal > interval.rightEndPoint)
			return search(T.leftChild, interval);
		return null;
	}
	/**
	 * Gets all intervals in this interval tree that intersect with a given interval.
	 * 
	 * @param q The query interval for which intersections are to be found
	 * @return Array list of all intersecting intervals; size is 0 if there are no intersections
	 */
	public ArrayList<Interval> findIntersectingIntervals(Interval q) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		ArrayList<Interval> IntersectingIntervals = new ArrayList<Interval>();
		IntersectingIntervals.addAll(queryTree(root, q));
		/*System.out.println(root.leftIntervals);
		System.out.println(root.rightIntervals);
		System.out.println(root.leftChild.leftIntervals);
		System.out.println(root.leftChild.rightIntervals);
		System.out.println(root.leftChild.leftChild.leftIntervals);
		System.out.println(root.leftChild.leftChild.rightIntervals);
		System.out.println(root.leftChild.rightChild.leftIntervals);
		System.out.println(root.leftChild.rightChild.rightIntervals);
		System.out.println(root.rightChild.leftIntervals);
		System.out.println(root.rightChild.rightIntervals);
		System.out.println(root.rightChild.leftChild.leftIntervals);
		System.out.println(root.rightChild.leftChild.rightIntervals);
		System.out.println(root.rightChild.rightChild.leftIntervals);
		System.out.println(root.rightChild.rightChild.rightIntervals);
		System.out.println(root.leftChild.leftChild.leftChild.leftIntervals);
		System.out.println(root.leftChild.leftChild.leftChild.rightIntervals);
		System.out.println(root.leftChild.leftChild.rightChild.leftIntervals);
		System.out.println(root.leftChild.leftChild.rightChild.rightIntervals);*/
		
		//System.out.println("INTERSECTING INTERVALS: " + IntersectingIntervals);
		return IntersectingIntervals;
	}
	private ArrayList<Interval> queryTree(IntervalTreeNode R, Interval interval)
	{
		ArrayList<Interval> ResultList = new ArrayList<Interval>();
		float splitVal = R.splitValue;
		System.out.println("NODESPLITVAL: " + splitVal );
		System.out.println("Node: " + splitVal);
		ArrayList<Interval> Llist = R.leftIntervals;
		//System.out.println(Llist);
		ArrayList<Interval> Rlist = R.rightIntervals;
		//System.out.println(Rlist);
		IntervalTreeNode Lsub = R.leftChild;
		IntervalTreeNode Rsub = R.rightChild;
		if (R.leftChild == null && R.rightChild == null)
		{
			ArrayList<Interval> Empty = new ArrayList<Interval>();
			System.out.println("EMPTY WAS REACHED");
			return Empty;
		}
		if (interval.contains(splitVal))
		{
			if (Llist != null)
			{
			ResultList.addAll(Llist);
			}
			//System.out.println("Result List Initial: " + ResultList);
			ResultList.addAll(queryTree(Rsub, interval));
			//System.out.println("Result List After Rsub: " + ResultList);
			ResultList.addAll(queryTree(Lsub, interval));
			//System.out.println("Result List After Lsub: " + ResultList);
		}
		else if (splitVal < interval.leftEndPoint)
		{
			System.out.println("PENIS");
			if (Rlist != null)
			{
				int i = Rlist.size()-1;
				//System.out.println("Rlist Size: " + i);
				//System.out.println(Rlist.get(i));
				while (i >= 0)
				{
					if (interval.intersects(Rlist.get(i)))
					{
						//System.out.println("LEFT END POINT: " + interval.leftEndPoint
						//		+ " RIGHT END POINT: " + interval.rightEndPoint);
						ResultList.add(Rlist.get(i));
					}
					i--;
					//System.out.println("Result List After While: " + ResultList);
				}
			}
			ResultList.addAll(queryTree(Rsub, interval));
		}
		else if (splitVal > interval.rightEndPoint)
		{
			int i = 0;
			if (Llist != null)
			{
				//System.out.println("Llist: " + Llist);
				//System.out.println("Llist Size: " + Llist.size());
				//System.out.println("Left End Point: " + Llist.get(i).leftEndPoint + " Right End Point: " + Llist.get(i).rightEndPoint);
				while (i < Llist.size())
				{
					if (interval.intersects(Llist.get(i)))
						{
							ResultList.add(Llist.get(i));
						}
					i++;
					//System.out.println("Count: " + i);	
				}
			}
			ResultList.addAll(queryTree(Lsub, interval));
		}
		return ResultList;
	}
}

