import java.util.ArrayList;
import java.util.List;


public class Display
{
	public static final int SIZE_X = 70;
	public static final int SIZE_Y = 20;
	final int WAIT_PER_FRAME = 30; 
	int frames=0;
	char[][] frame;
	int step;
	long start_time;
	Level level = new Level();	


	public Display()
	{
		step = 0;
		start_time = System.currentTimeMillis();	

	}

	public void print()
	{
		/* Si el tiempo actual - tiempo inicial > intevalo
		 * imprime el siguiente frame
		 */
		if( (System.currentTimeMillis() - start_time) > WAIT_PER_FRAME)
		{		
			update();
			start_time = System.currentTimeMillis();		
		}
	}

	private void update()
	{
		/* Carga el mapa en el frame 
		 * Limpia la pantalla
		 * Imprime el frame
		 */

		clean();
		System.out.println("Frame" + frames++);
		//printing chars
		for(int y=0; y<SIZE_Y; y++)
		{
			for(int x=0; x<SIZE_X; x++)
				System.out.print(frame[y][x]);
			System.out.print("\n");
		}
	}

	public void fillFrame(char[][] frameToFill)// for testing
	{
		frameToFill = new char[SIZE_Y][SIZE_X];
		for(int y=0; y<SIZE_Y; y++)
			for(int x=0; x<SIZE_X; x++)
				frameToFill[y][x]='0';	
	}

	//limpia el frame
	public void draw(){
		//deep copy of the array
		frame = new char[SIZE_Y][SIZE_X];
		for(int y=0; y<SIZE_Y; y++)
			for(int x=0; x<SIZE_X; x++)
				frame[y][x]=level.stages.get(0)[y][x];	
	}

	public void draw(char[][] asset, int y, int x)
	{
		for(int pos_y=0; pos_y<asset.length; pos_y++)
		{
			for(int pos_x=0; pos_x<asset[0].length; pos_x++)
				frame[SIZE_Y-(y+pos_y)-1][SIZE_X-(x+pos_x)-1]=asset[pos_y][pos_x];
				// inveritdo frame[SIZE_Y-(y+pos_y)-1][SIZE_X-(x+pos_x)-1]=asset[pos_y][pos_x];
		}
	}
	public void clean()
	{
		/* 
	     * Debuelve el cursor a la parte superior
	     */
	    final String ANSI_CLS = "\u001b[2J";
	    final String ANSI_HOME = "\u001b[H";
	    System.out.print(ANSI_CLS + ANSI_HOME);
	    System.out.flush();
	}

}