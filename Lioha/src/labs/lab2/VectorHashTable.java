package labs.lab2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*класс хэш таблицы*/
public class VectorHashTable implements IMyHashTable {

 /*   дефолтный размер массива*/
    public static final int DEFAULT_ARRAY_SIZE = 10;

    private int size;

    /*данные хранятся в массиве нодов*/
    private Node[] arr;

    public VectorHashTable() {
        arr = new Node[DEFAULT_ARRAY_SIZE];
    }

    public VectorHashTable(int arraySize) {
        arr = new Node[arraySize];
    }

    @Override
    public Vector add(Vector vector) {

        /*при добавлении нового елемента нужно определить его место*/
        int position = definePosition(vector);

        /*если на этой позиции никого нет, создаем новый нод и записываем его в массив*/
        if (arr[position] == null) {
            arr[position] = new Node(vector, null, null);
        } else {
            /*иначе записываем элемент из коллизией*/
            putWithCollision(vector, arr[position]);
        }
        size++;
        return vector;
    }


    /*метод удалет из структуры все елементы у которых значение У меньше чем параметр*/
    @Override
    public void remove(double y) {

        /*Для прохода по структуре и удаления используем итератор*/
        Iterator<Vector> iterator = iterator();
        while (iterator.hasNext()) {
            Vector val = iterator.next();
            if (val.getY() < y) {
                System.out.printf("removing %s \n", val);
                iterator.remove();
            }
        }
    }

    private void putWithCollision(Vector vector, Node firstNodeOnPosition) {
        /*находим ноду у которго нет следующей ноды и записываем туда новую ноду*/
        if (firstNodeOnPosition.nextNode == null) {
            firstNodeOnPosition.setNextNode(new Node(vector, null, firstNodeOnPosition));
            return;
        }
        putWithCollision(vector, firstNodeOnPosition.nextNode);
    }

    /*возвращает позицию вектора в массиве исходя из его хэш кода*/
    private int definePosition(Vector vector) {
        /*хэш код считается согласно заданию. целочисельным делением на хэш вектора (координата Х)*/
        return Math.abs(vector.hashCode() % arr.length);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator iterator() {
        return new VectorHashIterator();
    }

    @Override
    public String toString() {
        return "VectorHashTable{" +
                "size=" + size + "\n" + buildNodesString() +
                '}';
    }

    private String buildNodesString() {
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < arr.length; i++) {
            String riadok = String.format("%s komirka: %s \n", i, arr[i]);
            res.append(riadok);
        }
        return res.toString();
    }

    /*Итератор  нужен для того, чтоб проходится по данным в хэш структуре
    * он имеет 3 метода
    *   hasNext() - говорит есть ли в структуре следующий элемент
    *   next() - смотрит на следующий элемент и возвращает его
    *   remove() - удаляет елемент, на который смотрит*/
    private class VectorHashIterator implements Iterator<Vector> {

        Node currentNode;
        int cuurentArrIndex;
        Node nextNode;

        @Override
        public boolean hasNext() {
            if (size == 0) return false;
            return hasNextNode();
        }

        private boolean hasNextNode() {
            boolean iPlused = false;
            for (int i = cuurentArrIndex; i < arr.length; i++) {
                if (arr[i] != null) {
                    cuurentArrIndex = i;
                    if (currentNode == null || iPlused) {
                        nextNode = arr[i];
                        return true;
                    } else {
                        if (lookChildren(arr[i])) return true;
                    }
                    iPlused = true;
                }
            }
            return false;
        }

        private boolean lookChildren(Node node) {
            if (currentNode.equals(node)) {
                if (node.nextNode == null) return false;
                nextNode = node.nextNode;
                return true;
            }
            return lookChildren(node.nextNode);
        }

        @Override
        public Vector next() {
            currentNode = nextNode;
            return currentNode.getVector();
        }

        @Override
        public void remove() {
            int position = definePosition(currentNode.vector);
            if (arr[position] == null) throw new NoSuchElementException();
            removeElement(currentNode.vector, arr[position], position);
            currentNode = null;
            size--;
        }

        private Vector removeElement(Vector vector, Node node, int arrInd) {
            if (node.getVector().equals(vector)) {
                Vector res = node.getVector();
                if (node.nextNode == null) {
                    if (node.prevNode == null) {
                        arr[arrInd] = null;
                    } else {
                        Node prev = node.prevNode;
                        prev.nextNode = null;
                    }
                } else {
                /*has next node*/
                    if (node.prevNode == null) {
                        Node nextNode = node.nextNode;
                        nextNode.prevNode = null;
                        arr[arrInd] = nextNode;
                    } else {
                        Node next = node.nextNode;
                        Node prev = node.prevNode;
                        next.prevNode = prev;
                        prev.nextNode = next;
                    }
                }
                return res;
            }
            if (node.nextNode == null) throw new NoSuchElementException();
            return removeElement(vector, node.nextNode, arrInd);
        }
    }


    /*Для того, чтобы при возникновении коллизии не утратить данные
            используем обвертку для данных
            Нод имеет ссылку на предедущий нод и на следующий
    Данные в массиве будут хранится именно в нодах*/
    private static class Node {

        private Vector vector;
        private Node nextNode;
        private Node prevNode;

        public Node(Vector vector, Node nextNode, Node prevNode) {
            this.vector = vector;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }

        public Vector getVector() {
            return vector;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public Node getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vector=" + vector +
                    " => " + nextNodeStr(nextNode) +
                    '}';
        }


        private String nextNodeStr(Node node) {
            return node == null ? "" : nextNode.toString();
        }
    }
}
