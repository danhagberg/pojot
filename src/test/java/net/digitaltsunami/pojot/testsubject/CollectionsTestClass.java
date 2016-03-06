package net.digitaltsunami.pojot.testsubject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dhagberg on 3/5/16.
 */
public class CollectionsTestClass {
    private Set testSet;
    private List testList;
    private Map testMap;

    public Set getTestSet() {
        return testSet;
    }

    public void setTestSet(Set testSet) {
        this.testSet = testSet;
    }

    public List getTestList() {
        return testList;
    }

    public void setTestList(List testList) {
        this.testList = testList;
    }

    public Map getTestMap() {
        return testMap;
    }

    public void setTestMap(Map testMap) {
        this.testMap = testMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionsTestClass that = (CollectionsTestClass) o;

        if (testSet != null ? !testSet.equals(that.testSet) : that.testSet != null) return false;
        if (testList != null ? !testList.equals(that.testList) : that.testList != null) return false;
        return testMap != null ? testMap.equals(that.testMap) : that.testMap == null;

    }

    @Override
    public int hashCode() {
        int result = testSet != null ? testSet.hashCode() : 0;
        result = 31 * result + (testList != null ? testList.hashCode() : 0);
        result = 31 * result + (testMap != null ? testMap.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CollectionsTestClass{" +
                "testSet=" + testSet +
                ", testList=" + testList +
                ", testMap=" + testMap +
                '}';
    }
}
