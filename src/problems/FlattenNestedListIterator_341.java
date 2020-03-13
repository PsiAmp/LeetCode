package problems;

import java.util.*;

public class FlattenNestedListIterator_341 {


    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class RNestedInteger implements NestedInteger {
        List<NestedInteger> nints;
        Integer i = null;

        public RNestedInteger(List<NestedInteger> nints) {
            this.nints = nints;
        }

        public RNestedInteger(Integer i) {
            this.i = i;
        }

        @Override
        public boolean isInteger() {
            return nints == null;
        }

        @Override
        public Integer getInteger() {
            return i;
        }

        @Override
        public List<NestedInteger> getList() {
            return nints;
        }
    }

    public class NestedIterator implements Iterator<Integer> {

        Stack<List<NestedInteger>> niStack = new Stack<>();
        Stack<Integer> indicesStack = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            if (hasInt(nestedList) && nestedList != null && nestedList.size() != 0) {
                niStack.push(nestedList);
                indicesStack.push(0);
            }
        }

        private boolean hasInt(List<NestedInteger> nestedList) {
            for (int i = 0; i < nestedList.size(); i++) {
                NestedInteger nestedInteger = nestedList.get(i);
                if (nestedInteger.isInteger()) return true;
                else hasInt(nestedInteger.getList());

            }
            return false;
        }

        @Override
        public Integer next() {
            List<NestedInteger> currentList = niStack.peek();
            Integer currentIndex = indicesStack.pop();

            // Pop if it is last element in list
            if (currentIndex == currentList.size()-1) {
                niStack.pop();
            } else {
                indicesStack.push(currentIndex+1);
            }

            NestedInteger nestedInteger = currentList.get(currentIndex);
            if (nestedInteger.isInteger()) {
                return nestedInteger.getInteger();
            } else {
                List<NestedInteger> ints = nestedInteger.getList();
                if (!ints.isEmpty()) {
                    niStack.push(ints);
                    indicesStack.push(0);
                }
                return next();
            }
        }

        Deque<NestedInteger> iq = new ArrayDeque<>();
        Deque<Iterator> iters;

        private void init(List<NestedInteger> ni) {
            iters = new ArrayDeque<>();
            iters.add(ni.iterator());
        }

        public Integer pickNext() {
            return null;
            //iters.
        }

        @Override
        public boolean hasNext() {
            return !niStack.empty();
        }
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */

    public static void main(String[] args) {
        FlattenNestedListIterator_341 f = new FlattenNestedListIterator_341();

        List<NestedInteger> ii = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ii.add(new RNestedInteger(i));
        }

        List<NestedInteger> top = new ArrayList<>();
        for (int i = 10; i <= 50; i+=10) {
            if (i == 30)
                top.add(new RNestedInteger(ii));
            else top.add(new RNestedInteger(i));
        }

//        List<NestedInteger> top = new ArrayList<>();
//        top.add(new RNestedInteger(new ArrayList<>()));

        NestedIterator ni = f.new NestedIterator(top);

        while (ni.hasNext()) {
            System.out.println(ni.next());
        }
    }

}
