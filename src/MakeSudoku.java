import java.util.SplittableRandom;



public class MakeSudoku 
{
	public int[][] grille = new int[9][9];
	
	public int lives;
	
	public int givenIndices;
	
	public MakeSudoku()
	{
		
		lives = 3;
		
		
		String[] row = {"", "", "", "", "", "", "", "", ""};
		String[] column = {"", "", "", "", "", "", "", "", ""};
		
		boolean testSudoku = true;
		
		for(int i = 0 ; i < 9 ; i += 3)
		{
			for(int l = 0 ; l < 9 ; l += 3)
			{
				
				if(!testSudoku)
				{
					
					
					i = 0;
					l = 0;
					row = new String[] {"", "", "", "", "", "", "", "", ""};
					column = new String[] {"", "", "", "", "", "", "", "", ""};
					grille = new int[9][9];
					
				}
				
				
				testSudoku = makeSection(grille, row, column, i, l);
				
				
				
				if(i >= 6 && l >= 3 && !testSudoku)
				{
					i = 0;
					l = 0;
				}
				
			}
		}
		
		
		
		/*
		for(int l = 0 ; l < 9 ; l++)
		{
			
			for(int y = 0 ; y < 9 ; y++)
			{
				System.out.print(grille[l][y]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
		
		*/
		
	}
	
	public int mistake()
	{
		return lives--;
	}
	

	public boolean makeSection(int[][] sect, String[] row, String[] col, int indexRow, int indexCol)
	{
		
		String used = "";
		
		
		String[] backupRow = {row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]};
		String[] backupCol = {col[0], col[1], col[2], col[3], col[4], col[5], col[6], col[7], col[8]};
		
		SplittableRandom rn = new SplittableRandom();
		
		
		
		int val = rn.nextInt(9) + 1;
		
		String valStr = Integer.toString(val);
		
		int stuckCpt = 0;
		
		int resetCpt = 0;
		
		for(int y = 0 ; y < 3 ; y++)
		{
			
			for(int z = 0 ; z < 3 ; )
			{
				
				if(resetCpt >= 9)
				{
					
					return false;
					
				}
				
				if(stuckCpt > 10)
				{
					y = 0;
					z = 0;
					stuckCpt = 0;
					used = "";
					resetArr(row, col, backupRow, backupCol, indexRow, indexCol);
					
					resetCpt++;
				}
				
				if(!used.contains(valStr))	
				{
					
					if(!row[y+indexRow].contains(valStr) && !col[z+indexCol].contains(valStr))
					{
						
						
						sect[y+indexRow][z+indexCol] =  val;
						used += valStr;
					
						row[y+indexRow] += valStr;
						col[z+indexCol] += valStr;
					
					
						z++;
					}
					else 
					{
						stuckCpt++;
					}
					
				}
				val = rn.nextInt(9 - 1 + 1) + 1;
				valStr = Integer.toString(val);
			}
		}
		
		return true;
	}
	
	public void resetArr(String[] row, String[] col, String[] backRow, String[] backCol, int indexRow, int indexCol)
	{
		
		for(int i = indexRow ; i < 9 ; i++)
		{
			
			row[i] = backRow[i];
			
			
		}
		
		for(int l = indexCol ; l < 9 ; l++)
		{
			
			col[l] = backCol[l];
			
			
		}
		
		
	}
	
	public int[][] getGrid()
	{
		return grille;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public int getIndices()
	{
		return givenIndices;
	}
	
	
}
