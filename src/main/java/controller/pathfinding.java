package controller;

public class pathfinding implements IA {
	public void AStar() {
		ArrayList<Node> priority = new ArrayList<Node>();
		priority.add(map[startx][starty]);
		while(solving) {
			if(priority.size() <= 0) {
				solving = false;
				break;
			}
			int hops = priority.get(0).getHops()+1;
			ArrayList<Node> explored = exploreNeighbors(priority.get(0),hops);
			if(explored.size() > 0) {
				priority.remove(0);
				priority.addAll(explored);
				Update();
				delay();
			} else {
				priority.remove(0);
			}
			sortQue(priority);	//SORT THE PRIORITY QUE
		}
	}
}
