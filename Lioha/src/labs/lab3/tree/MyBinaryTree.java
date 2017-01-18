package labs.lab3.tree;

import labs.lab3.model.Course;
import labs.lab3.model.Stipendia;
import labs.lab3.model.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/*реализация бинарного дерева*/
public class MyBinaryTree implements IMyBinaryTree {

    /*елементы хранятся в виде нодов
    * ссылка на коернь дерева*/
    private Node root;
    private int size;

    /*добавление происходит по ключу
    * согласно с вариантом это номер студака
    * одинаковых елементов быть в структуре не может
    * при добавлении если корень нулл, то создаем корень
     * иначе рекурсивно ищем место для нового нода*/
    @Override
    public Student add(Integer key, Student student) {
        if (root == null) {
            root = new Node(key, student, null, null, null);
            return root.value;
        }
        Student added = recursieveAdd(root, key, student);
        size++;
        return added;
    }

    /*смотрим, если ключ больше ключа текущего нода, то идем вправо
    * иначе идем влево
    * находим свободное место и добавляем туда новый елемент связывая ноды между собой*/
    private Student recursieveAdd(Node root, Integer key, Student student) {
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) throw new UnsupportedOperationException("This key is already in tree");
        if (compareRes < 0) {
            /*if root<input*/
            if (root.right == null) {
                root.right = new Node(key, student, root, null, null);
                return root.right.value;
            } else {
                return recursieveAdd(root.right, key, student);
            }
        } else {
            /*if root>input*/
            if (root.left == null) {
                root.left = new Node(key, student, root, null, null);
                return root.left.value;
            } else {
                return recursieveAdd(root.left, key, student);
            }
        }
    }

    /*обход дерева паралельно (левый ребенок, корень. правый ребенок) и формируем строку с результатами*/
    @Override
    public String getStringTreeParalel() {
        return goThroughTreeParalel(root, 0, new StringBuilder("Tree structure\n")).toString();
    }

    /*обход дерева в ширину (корень, левый ребенок, правый ребенок) и формируем строку с результатами*/
    @Override
    public String getStringTreeShirina() {
        return goThroughTreeShirinu(root, 0, new StringBuilder("Tree structure\n")).toString();
    }

    private StringBuilder goThroughTreeParalel(Node root, int level, StringBuilder res) {
        if (root == null) return res;
        goThroughTreeParalel(root.left, level + 1, res);
        res.append(buildString(level, root));
        goThroughTreeParalel(root.right, level + 1, res);
        return res;
    }

    private StringBuilder goThroughTreeShirinu(Node root, int level, StringBuilder res) {
        if (root == null) return res;
        res.append(buildString(level, root));
        goThroughTreeShirinu(root.left, level + 1, res);
        goThroughTreeShirinu(root.right, level + 1, res);
        return res;
    }

    /*запись елемента в строку результата согласно с уровнем, на котором стоит узел
    * тоесть количество "__" перед результатом соответствует уровню узла в дереве*/
    private String buildString(int level, Node root) {
        StringBuilder spaces = new StringBuilder("__");
        for (int i = 0; i < level; i++) {
            spaces.append("__");
        }
        return spaces.append(root.value).append("\n").toString();
    }

    @Override
    public int size() {
        return size;
    }

    /*возвращает лист студентов, которые соответствуют требованиям
    * согласно варианту обход дерева должен быть паралельным, поэтому
    * обходим дерево паралельным способом, потом отбираем результаты, которые подходят*/
    @Override
    public List<Student> findByCriteria(Course course, Stipendia stipuha) {
        List<Student> all = new LinkedList<>();
        goThroughTreeParalel(root, all);
        return all.stream().filter(student -> student.getStipendia().equals(stipuha) && student.getCourse().equals(course)).collect(Collectors.toList());
    }

    /*реализация паралельного обхода
    * обходим дерево и записываем все елементы в лист результат*/
    private void goThroughTreeParalel(Node root, List<Student> res) {
        if (root == null) return;
        res.add(root.value);
        goThroughTreeParalel(root.left, res);
        goThroughTreeParalel(root.right, res);
    }


    /*удаление елемента
    * проверяются все возможные позиции елемента в дереве и соответсвенно изменяются связи нодов для корректного удаления елемента*/
    @Override
    public Student remove(Integer key) {
        Student res;
        /*снвчала смотрим если елемент, который нужно удалить - корень*/
        if (root.key.compareTo(key) == 0) {
            res = root.value;
            if (root.right == null && root.left == null) {
                root = null;
            } else if (root.left != null && root.right == null) {
                root = root.left;
                root.parent = null;
            } else if (root.right != null && root.left == null) {
                root = root.right;
                root.parent = null;
            } else if (root.left != null && root.right != null) {
                Node newRoot = findNewRootLeaf(root.right);
                res = newRoot.value;
                if (newRoot.right == null) {
                    if (newRoot.parent != null) newRoot.parent.left = null;
                    newRoot.parent = null;
                    root.left.parent = newRoot;
                    root.right.parent = newRoot;
                    newRoot.left = root.left;
                    newRoot.right = root.right;
                    root = newRoot;
                } else {
                    if (newRoot.parent != null) {
                        if (newRoot.right != null) {
                            newRoot.parent.left = newRoot.right;
                        } else {
                            newRoot.parent.left = null;
                        }
                    }
                    newRoot.parent = null;
                    root.left.parent = newRoot;
                    root.right.parent = newRoot;
                    newRoot.left = root.left;
                    newRoot.right = root.right;
                    root = newRoot;
                }
            }
        } else {
            /*иначе, если не корень ищем елемент рекурсивно*/
            Node founded = findRecursieve(root, key);
            res = founded.value;
            if (founded.right != null) {
                /*если елемент имеет детей справа*/
                Node mestoFounded = findNewRootLeaf(founded.right);
                founded.parent.left = mestoFounded;
                mestoFounded.parent.left = null;
                mestoFounded.parent = founded.parent;
                mestoFounded.right = founded.right;
                founded.right.parent = mestoFounded;
                if (founded.left != null) {
                    /*и слева тоже*/
                    mestoFounded.left = founded.left;
                    founded.left.parent = mestoFounded;
                }
            } else {
                /*если елемент не имеет детей справа*/
                if (founded.left != null) {
                    /*если елемент имеет детей слева*/
                    founded.left.parent = founded.parent;
                    founded.parent.left = founded.left;
                } else {
                    /*если елемент не имеет детей слева (и справа тоже)
                    * смотрим чей я ребенок и удаляем связь*/

                    /*if founded is left children*/
                    if (null != founded.parent.left && founded.parent.left.key.compareTo(key) == 0)
                        founded.parent.left = null;
                    /*if founded is right children*/
                    if (null != founded.parent.right && founded.parent.right.key.compareTo(key) == 0)
                        founded.parent.right = null;
                }
            }
        }
        size--;
        return res;
    }

    /*бежим по дереву бинарным поиском
    * если я больше - иду вправо
    * если меньше - иду влево
    * если я такой же - возвращаю ссылку на найденный нод*/
    private Node findRecursieve(Node root, Integer key) {
        if (root == null) throw new NoSuchElementException("No such element in tree with key: " + key);
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) return root;
        if (compareRes < 0) {
            /*root<key
            * go right*/
            return findRecursieve(root.right, key);
        } else {
            /*root>key
            * go left*/
            return findRecursieve(root.left, key);
        }
    }

    /*ищем наименьший листок
    * тоесть постоянно идем влево вниз*/
    private Node findNewRootLeaf(Node startPoint) {
        if (startPoint.left == null) return startPoint;
        return findNewRootLeaf(startPoint.left);
    }


    /*класс в котором будут хранится данные
    * иметт ключ, значение,
    * ссылку на отцовский нод, левого и правого ребенка для реализации операций бинарного дерева*/
    private class Node {

        Integer key;
        Student value;
        Node parent;
        Node left;
        Node right;

        public Node(Integer key, Student value, Node parent, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

    }


}
