import java.io.*;
public class Map {
  String name;
  int numTiles;
  int height;
  int width;
  int numWaterTiles;
  int numDesertTiles;
  int numRegularTiles;
  int map[][];
  Territory terrs[];
  String names[][]= new String[height][width];
  public Map(){
    name = "defaultmap.csv";
    mapGen();
  }
  public Map(String mapName){
    name = mapName;
    mapGen();
  }
  private void mapGen(){
    Scanner mapScanner = new Scanner(new File(name));
    numTerritories = mapScanner.nextInt();
    height = mapScanner.nextInt();
    width = mapScanner.nextInt();
    numWaterTerritories = mapScanner.nextInt();
    numDesertTerritories = mapScanner.nextInt();
    numRegularTerritories = mapScanner.nextInt();
    map = new int[height][width];
    int sTroops[][] = new int[height][width];
    for(int y = 0; y < height; y ++) for(int x = 0; x < height; x ++){
      map[y][x] = mapScanner.nextInt();
      }
    int terrains[] = new int[numTerritories];
    for(int i = 0; i < numTerritories; i++){
      terrains[i] = mapScanner.nextInt();
      sTroops[i] = mapScanner.nextInt();
      names[i] = mapScanner.nextInt();
    }
    boolean areAdjacent[][] = new boolean[numTiles][numTiles];
    boolean areDiagonal[][] = new boolean[numTiles][numTiles];
    for(int a = 0; a < numTiles; a ++) for(int b = 0; b < numTiles; b ++){
      areAdjacent[a][b] = false;
      areDiagonal[a][b] = false;
    }
    for(int y = 0; y < height; y ++) for(int x = 0; x < width; x ++){
      areDiagonal[map[y][x]][map[(y+1)%height][(x+1)%width]] = true;
      areDiagonal[map[y][x]][map[(y-1+height)%height][(x-1+width)%width]] = true;
      areDiagonal[map[y][x]][map[(y-1+height)%height][(x+1)%width]] = true;
      areDiagonal[map[y][x]][map[(y+1)%height][(x-1+width)%width]] = true;
    }
    for(int y = 0; y < height; y ++) for(int x = 0; x < width; x ++){
      areAdjacent[map[y][x]][map[(y+1)%height][x]] = true;
      areDiagonal[map[y][x]][map[(y+1)%height][x]] = false;
      areAdjacent[map[y][x]][map[(y-1+height)%height][x]] = true;
      areDiagonal[map[y][x]][map[(y-1+height)%height][x]] = false;
      areAdjacent[map[y][x]][map[y][(x+1)%width]] = true;
      areDiagonal[map[y][x]][map[y][(x+1)%width]] = false;
      areAdjacent[map[y][x]][map[y][(x-1+width)%width]] = true;
      areDiagonal[map[y][x]][map[y][(x-1+width)%width]] = false;
    }
    int n = 0;
    int adjacent[][];
    int diagonal[][];
    while(n < numTerritories){
      int na = 0;
      int nd = 0;
      for(int k = 0; k < numTerritories; k++){
        if(areAdjacent[n][k]) na ++;
        if(areDiagonal[n][k]) nd ++;
      }
      adjacent[n] = new int[na];
      diagonal[n] = new int[nd];
      for(int k = 0; k < numTerritories; k++){
        if(areAdjacent[n][k]){
          na ++;
          adjacent[n][adjacent[n].length - na] = k;
        }
        if(areDiagonal[n][k]){
          nd ++;
          diagonal[n][diagonal[n].length - nd] = k;
        }
      }
      n ++;
    }
    for(int i = 0; i < numTerritories; i++){
      terrs[i] = new Territory(names[i], sTroops[i], terrain[i], adjacent[i], diagonal[i]);
    }
  }
  public int getTile(int row, int col) return map[row][col];
  public int getAdjacentTerrs[](int terr) return adjacent[terr];
  public int getDiagonalTerrs[](int terr) return diagonal[terr];
  public int getTerrSize(int terr) return terr[terr].getSize();
  public int getTerrType(int terr) return terr[terr].getType();
}
