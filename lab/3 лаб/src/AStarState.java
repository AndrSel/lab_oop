import java.util.*;

public class AStarState
{// Создадим хеш-карточи
    private Map2D map;
    private HashMap<Location, Waypoint> Opened = new HashMap<Location, Waypoint>();
    private Map<Location, Waypoint> Closed = new java.util.HashMap<Location, Waypoint>();

    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");
        this.map = map;
    }

    public Map2D getMap()
    {
        return map;
    }

    public Waypoint getMinOpenWaypoint()
    {
        if (Opened.size() == 0) return null;//null, если размер "открытых"=0
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>(Opened.values());//values-получить все занчения открытых вершин
        //for (Waypoint k: waypoints){System.out.println(k);}//вывод всех вершин"Открытых"
        //Set<Location> w = Opened.keySet();//Вывод всех координат открытых точек
        //for (Location k:w){System.out.println(k);}

        //ArrayList<Waypoint> waypoints1 = new ArrayList<Waypoint>(Closed.values());
        //for (Waypoint k: waypoints1){System.out.println(k);}//вывод всех вершин"Зактрытх"
        //Set<Location> w = Opened.keySet();//Вывод всех координат "Зактрытх" точек
        //for (Location k:w){System.out.println(k);}


        float mincost = waypoints.get(0).getTotalCost();//Возвращае т оценку общей стоимости 0-го значение
        Waypoint min = waypoints.get(0);//назначается минимальной точкой самая первая точка
        for (int i = 0; i < waypoints.size(); i++) {
            if (waypoints.get(i).getTotalCost() < mincost) {//если оценка общей стоимости перебираемой точки меньше mincost:
                min = waypoints.get(i);//min=номеру той точки в которой общая стоимость пути меньше mincost
                mincost = min.getTotalCost();//mincost присваевается значение стоимости пути точки min
            }
        }
        return min;//возвращается координата с наименьшей стоимостью пути
    }


    public boolean addOpenWaypoint(Waypoint newWP) {//!!!изменен
        if (Opened.get(newWP.getLocation()) == null ) {//Если в наборе «открытых вершин»
            // в настоящее время нет вершины
            //для данного местоположения, то необходимо просто добавить новую вершину.
            Opened.put(newWP.getLocation(), newWP);
            //Opened.put(Возвращает местоположение путевой точки,)

            return true;
        }
        else
        {                  // координаты вершины .. вызов метода, Возвающего фактическую стоимость перехода к этой точке с
            // самого начала для данных координат
            //!!! из Opened по ключу(локации) вызываем значение с типом Waypoint и от него получаем факт. стоимость методом getPreviousCost
            if ((Opened.get(newWP.getLocation())).getPreviousCost() <//!!!!!после изменения:факт. стоимость новой вершины < текущей  !!!! так было до изменения:стоимсоть текщей вершины>стоимости новой вершины
        //Возвращает фактическую стоимость перехода к этой точке с
        //самого начала для данных координат

                    newWP.getPrevious().getPreviousCost()) {
                Opened.put(newWP.getLocation(), newWP);
                //System.out.println("новыя: "+ (Opened.get(newWP.getLocation())));
                //System.out.println("Новаятест: "+ newWP);


                return true;
            }

        }
        return false;
    }


    public boolean addOpenWaypoint1(Waypoint newWP) {//!!!!до изменений
        if (Opened.get(newWP.getLocation()) == null ) {//Если в наборе «открытых вершин»
            // в настоящее время нет вершины
            //для данного местоположения, то необходимо просто добавить новую вершину.
            Opened.put(newWP.getLocation(), newWP);
            //Opened.put(Возвращает местоположение путевой точки,)

            return true;
        }
        else
        {                  // координаты вершины .. вызов метода, Возвающего фактическую стоимость перехода к этой точке с
            // самого начала для данных координат
            //!!! из Opened по ключу(локации) вызываем значение с типом Waypoint и от него получаем факт. стоимость методом getPreviousCost
            if ((Opened.get(newWP.getLocation())).getPreviousCost() >//!!!! так было до изменения:стоимсоть текщей вершины>стоимости новой вершины
                    //Возвращает фактическую стоимость перехода к этой точке с
                    // самого начала для данных координат

                    newWP.getPreviousCost()) {
                Opened.put(newWP.getLocation(), newWP);
                return true;
            }
        }
        return false;
    }



    public int numOpenWaypoints()//Узнаем размер
    {
        return Opened.size();
    }

    public void closeWaypoint(Location loc)
    {
        Closed.put(loc, Opened.remove(loc));//(КООРДИНАТЫ[ключ],V put(K k, V v):помещает в коллекцию
        // новый объект
        // с ключом k и значением v.
        // Если в коллекции уже есть объект с подобным ключом, то он перезаписывается.. )
        //remove-Удаляет элемент из вызывающего списка на позиции
        //по указанному индексу и возвращает удаленный элемент.
    }

    public boolean isLocationClosed(Location loc)/*Возвращает логическое
    значение true, если
    вызывающее отображение содержит указанный
    ключ k, а иначе - логическое значение false */
    {
        if (Closed.containsKey(loc)) return true;
        return false;
    }
}
