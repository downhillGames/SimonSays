import java.util.concurrent.ThreadLocalRandom;


public class Map {
//private int [] createdmap = int[15];
private static int map_array[] = new int [27];

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


while (numberOfBlocks < 9) {
	
	
	
	int randomX = ThreadLocalRandom.current().nextInt(min_x, max_x + 1);
	int randomY = ThreadLocalRandom.current().nextInt(min_y, max_y + 1);
	int i = 0;
	while_tries = 0;
	
	while (i < (numberOfBlocks * 3) && while_tries < 100000)
	{
		if (Math.abs(map_array[i + 1]- randomX) < minDistanceAllowed || Math.abs(map_array[i + 1] - randomY) < minDistanceAllowed )
		{
			randomX = ThreadLocalRandom.current().nextInt(min_x, max_x + 1);
			randomY = ThreadLocalRandom.current().nextInt(min_y, max_y + 1);
			i = 0;
			while_tries += 1;
		}
		else
		{
		i += 3;
		//while_tries = 0;
		}
		}
	map_array [(numberOfBlocks * 3) - 1] = randomX;
	map_array [(numberOfBlocks * 3) - 2] = randomY;
	map_array[(numberOfBlocks * 3) - 3] = numberOfBlocks;
	numberOfBlocks += 1;
	}
	}

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

public int[] getMap_array() {
	return map_array;
	
	
}
public static void setMap_array(int map_array[]) {
	Map.map_array = map_array;
}


}