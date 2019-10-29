import java.util.concurrent.ThreadLocalRandom;


public class Map {
	private static int map_array[] = new int [27];
	
	/*Creates map for main game - Array goes: index, X-coordinate, Y-coordinate - attempts 100,000 times for coordinates to not be too close*/
	void createMap() {
	int min_x = 75;
	int max_x = 1125;
	int min_y = 75;
	int max_y = 525;
	int while_tries = 0;
	int minDistanceAllowed = 50;
	System.out.println("createdMap");
	int numberOfBlocks = 0;
	
	
	map_array[1] = ThreadLocalRandom.current().nextInt(min_x, max_x + 1);
	map_array[2] = ThreadLocalRandom.current().nextInt(min_y, max_y + 1);
	map_array[0] = numberOfBlocks;
	numberOfBlocks += 1;
	
	//we need 9 block =]
	while (numberOfBlocks < 10) {
		
		
		
		int randomX = ThreadLocalRandom.current().nextInt(min_x, max_x + 1);
		int randomY = ThreadLocalRandom.current().nextInt(min_y, max_y + 1);
		int i = 0;
		while_tries = 0;
		while (i < (numberOfBlocks * 3) && while_tries < 100000)
		{
			// if coordinates are too close together, attempt to coordinates that are not too close
			if (Math.abs(map_array[i + 1]- randomX) < minDistanceAllowed && Math.abs(map_array[i + 1] - randomY) < minDistanceAllowed )
			{
				randomX = ThreadLocalRandom.current().nextInt(min_x, max_x + 1);
				randomY = ThreadLocalRandom.current().nextInt(min_y, max_y + 1);
				i = 0;
				while_tries += 1;
			}
			// if coordinates are good, move on
			else{
			i += 3;
			}
		}
		map_array [(numberOfBlocks * 3) - 1] = randomY;
		map_array [(numberOfBlocks * 3) - 2] = randomX;
		map_array[(numberOfBlocks * 3) - 3] = numberOfBlocks;
		numberOfBlocks += 1;
		}
	}
	
	/*returns the absolute value of the two input integers*/
	public static int abs(int value1, int value2) {
		int result = value1 - value2;
		
		if (result >= 0)
		{
			return result;
		}
		else {
			return -result;
		}
		
	}
	
	/*Getters and Setters*/
	public int[] getMap_array() {
		return map_array;
		
		
	}
	public static void setMap_array(int map_array[]) {
		Map.map_array = map_array;
	}


}