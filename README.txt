Author: Yufan Wang
Date: 10/01/2019



USAGE: {
    In the test class, in the finalize() func, there's three lines of function call.
    PLEASE UNCOMMENT the algorithm you wanna run!
    There're also 5 test cases, each corresponded with a yard.
}


Important things: {

    a. For problem 1. 2, and 3, I didn't write explicit test cases, but you could either
        - look at the function codes
        - verify output of DFS, BFS, A* algorithms

    b. The BFS and DFS are for Problem 4
        - My BFS solves YARD-1 in around 14 seconds
          (Worth-noting, Since it says on PDF never try YARD1 with blind search
        - There's a counter in BFS that you can uncomment, to see how many states are traversed

    c. SEARCH SPACE for Problem 5
        - Suppose c cars on t tracks
        - |SearchSpace| = (c+t-1)! / (t-1)!

        - For example, suppose 5 cars on 6 tracks
        - |SS| = 6*7*8*9*10

    d. A* for Problem 6
        - I've commented the HEURISTIC function in-lind, PLEASE go see it
        - It solve YARD2 3x times faster than BFS (from 450ms to 150ms)
          but improves little on YARD1 (from 12s to 9s)
        - I'm sure it finds the optimal solution

    e. Heuristic Function for A*
        - tmp_score starts at 0;
        - traverse track 1 of GOAL state, left to right{
            consider k = (ith element of GOAL_track_1),
            if ith element of CURRENT_track_1 (exists) and (does not equal to k)
                tmp_score ++;
            result doubles at the end of every loop.
        }
        - return tmp_score
}