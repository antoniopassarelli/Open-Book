# Assumptions  

Being very similar to the minimum cost path algorithm, the input strings can be stored as an **integers matrix** with zero padding:  

7  
6  3  
3  8 5  
11 2 10 9  
  
will be stored as: 
  
7 0 0 0  
6 3 0 0  
3 8 5 0  
11 2 10 9  


Once this matrix has been created, starting from the penultimate row, for each column (starting from the penultimate) is found the minimum value between the current column and the next one of the row below.  
Such vale is added to the above row's (the one currently in examination) column-th value.  
The lowest column's index is stored into an auxiliary matrix which will be useful in order to retrieve the minimum path.  
Finally, in order to retrieve the minimum path, it will just be necessary to start from the 1st row of the auxiliary matrix and follow the value of the above row at column 0 (aux[0]).  This value indicates the index of the current row's column which is part of the minimum path.