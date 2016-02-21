package net.digitaltsunami.pojot;

import java.beans.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Tool to provide basic validation of POJO classes in areas related to getters/setters, toString,
 * equals, and hashCode methods. These tests are designed for classes with no special logic associated with
 * setters/getters.
 *
 * <h3>Results</h3>
 * Results of tests are returned via a List of error strings indicating the field in error and if applicable, the
 * expected results.
 *
 * <h3>Getter and Setter Testing</h3>
 * The default behavior is to select all properties for testing.  Getters and setters will be tested to ensure that
 * they simply set or retrieve the value for a property.  These will be tested with a variety of values applicable to
 * the data type. If a property has special logic, then it should be excluded from processing.  If only one of
 * the methods for the property has special processing, then the method should be excluded.
 * <p>
 * Testing of getters and setters is included in {@link #validate()}, but can be invoked directly using
 * {@link #runGetterTests()} or {@link #runSetterTests()}
 *
 * <pre>
 * {@code
 * List<String> errors = new TestAid<SimpleClass>(SimpleClass.class)
 *                              .excludeFields("specialProp1", "specialProp2")
 *                              .excludeSetters("propWithSetterLogic")
 *                              .validate();
 * }
 * </pre>
 *
 * <h3>Equals and HashCode Testing</h3>
 * Equals and hashCode testing are expected to use the same fields.  If no fields are specified, then the equals
 * and hashCode methods are tested to ensure that the default methods are used.  If fields are specified, then
 * testing will ensure that the methods return equal values when the define properties are the same and differ as they
 * differ.
 * <p>
 * Testing of equals and hashCode is included in {@link #validate()}, but can be invoked directly using
 * {@link #runEqualsTests()} or {@link #runHashTests()}
 * <p>
 * <em>Note that the fields are not affected by the inclusion/exclusion mentioned above.</em>
 * </p>
 * <pre>
 * {@code
 * List<String> errors = new TestAid<SimpleClass>(SimpleClass.class)
 *                              .excludeFields("customerId")
 *                              .includeInEquals("customerId", "userId")
 *                              .validate();
 * }
 * </pre>
 *
 */
public class TestAid<T> {
    private final Class clazz;
    private final BeanInfo beanInfo;
    private final HashMap<String, MethodDescriptor> beanMethodsMap = new HashMap<>();

    private final HashSet<String> includeFields = new HashSet<>();
    private final HashSet<String> excludeFields = new HashSet<>();
    private final HashSet<String> includeGetters = new HashSet<>();
    private final HashSet<String> excludeGetters = new HashSet<>();
    private final HashSet<String> includeSetters = new HashSet<>();
    private final HashSet<String> excludeSetters = new HashSet<>();
    private final HashSet<String> includeInEquals = new HashSet<>();

    /**
     * Create a test aid for the provided class.
     *
     * @param clazz Class under test.  Cannot be null.
     * @throws IntrospectionException if failed to extract property and method information from class.
     */
    public TestAid(Class clazz) throws IntrospectionException {
        this.clazz = clazz;
        this.beanInfo = Introspector.getBeanInfo(this.clazz, this.clazz.getSuperclass());
        for (MethodDescriptor m : beanInfo.getMethodDescriptors()) {
            beanMethodsMap.put(m.getDisplayName(), m);
        }
    }

    /**
     * Return the name of the class under test.
     * @return Fully qualified name of class under test.
     */
    public String getClassName() {
        return clazz.getName();
    }

    /**
     * Exclude a set of fields from being tested.  Provided fields will not be included in getter/setter testing.
     * Fields should be excluded when specialized logic is performed for these fields, as this logic might cause
     * the basic validation to fail.
     * <p>
     * If a fields is excluded, then that field is excluded from all getter/setter processing regardless of
     * other settings.
     * <p>
     * If the number of fields being excluded represents the majority of the fields, then it may be better to use
     * {@link #includeFields(String...)}.
     * @param fields Names of fields to exclude from processing.
     * @return Current instance to allow fluent invocation.
     * @see #includeFields(String...)
     */
    public TestAid<T> excludeFields(String... fields) {
        for (String field : fields) {
            excludeFields.add(field);
        }
        return this;
    }

    /**
     * Specify a set of fields for getter/setter testing.  The default behavior is to include all fields for testing.
     * If specified, only the provided fields will be included in getter/setter testing.
     * Only fields with no specialized logic should be included, as this logic might cause the basic validation to fail.
     * <p>
     * If the number of fields being included represents the majority of the fields, then it may be better to use
     * {@link #excludeFields(String...)}.
     * @param fields Names of fields to include in processing.
     * @return Current instance to allow fluent invocation.
     * @see #excludeFields(String...)
     */
    public TestAid<T> includeFields(String... fields) {
        for (String field : fields) {
            includeFields.add(field);
        }
        return this;
    }

    /**
     * Exclude a set of getter methods from testing.  Provided fields will not be included in getter testing.
     * Fields should be excluded when specialized logic is performed for these fields, as this logic will cause
     * the basic validation to fail.
     * <p>
     * Note that is to exclude the getter processing from a field that is currently included. If the field is not
     * included for processing, then this call is redundant.
     * @param fields Names of fields whose getters should be excluded.
     * @return Current instance to allow fluent invocation.
     */
    public TestAid<T> excludeGetters(String... fields) {
        for (String field : fields) {
            excludeGetters.add(field);
        }
        return this;
    }

    /**
     * Include a set of getter methods from testing.  Provided fields will be included in getter testing provided
     * that they have not been filtered out with field level filters.
     * @param fields Names of fields whose getters should be excluded.
     * @return Current instance to allow fluent invocation.
     */
    public TestAid<T> includeGetters(String... fields) {
        for (String field : fields) {
            includeGetters.add(field);
        }
        return this;
    }

    /**
     * Exclude a set of setter methods from testing.  Provided fields will not be included in setter testing.
     * Fields should be excluded when specialized logic is performed for these fields, as this logic will cause
     * the basic validation to fail.
     * <p>
     * Note that is to exclude the setter processing from a field that is currently included. If the field is not
     * included for processing, then this call is redundant.
     * @param fields Names of fields whose setters should be excluded.
     * @return Current instance to allow fluent invocation.
     */
    public TestAid<T> excludeSetters(String... fields) {
        for (String field : fields) {
            excludeSetters.add(field);
        }
        return this;
    }

    /**
     * Include a set of setter methods from testing.  Provided fields will be included in setter testing provided
     * that they have not been filtered out with field level filters.
     * @param fields Names of fields whose setters should be excluded.
     * @return Current instance to allow fluent invocation.
     */
    public TestAid<T> includeSetters(String... fields) {
        for (String field : fields) {
            includeSetters.add(field);
        }
        return this;
    }

    /**
     * Set of fields that are used to test equality and hashCode logic of the class under test.
     * <p>
     * NOTE: The that the default set of fields is empty. Equals and hashCode testing will be performed only if
     * this set includes at least one field.
     * <p>
     * These fields are not affected by the include/exclude fields filters and will be tested using
     * values allowable for the type and nulls where applicable.
     * @param fields Names of fields to use in equality testing.
     *
     * @return Current instance to allow fluent invocation.
     */
    public TestAid<T> includeInEquals(String... fields) {
        for (String field : fields) {
            includeInEquals.add(field);
        }
        return this;
    }

    /**
     * Run a suite of basic validation tests based on current configuration.
     * <p>
     * Current set of tests that are run:
     * <ul>
     *     <li>Getter tests</li>
     *     <li>Setter tests</li>
     *     <li>Hashcode test</li>
     *     <li>Equals test</li>
     *     <li>ToString test</li>
     * </ul>
     *
     * @return List of all validations that failed.  If empty, then validation was successful.
     */
    public List<String> validate() {
        final List<String> errors = runGetterTests();
        errors.addAll(runSetterTests());
        errors.addAll(runHashTests());
        errors.addAll(runEqualsTests());
        errors.addAll(runToStringTests());
        return errors;
    }

    /**
     * Run getter tests on all applicable properties.  Applicable properties are defined
     * by the include/exclude settings previously set.
     *
     * @return List of zero to many errors found during the run.
     */
    public List<String> runGetterTests() {
        final List<String> errors = new ArrayList<>();
        Set<PropertyDescriptor> fields = getGettersToCheck();
        if (!fields.isEmpty()) {
            errors.addAll(new GetterTestRunner(clazz, beanInfo, fields).runTests());
        }
        return errors;
    }

    /**
     * Run setter tests on all applicable properties.  Applicable properties are defined
     * by the include/exclude settings previously set.
     *
     * @return List of zero to many errors found during the run.
     */
    public List<String> runSetterTests() {
        final List<String> errors = new ArrayList<>();
        Set<PropertyDescriptor> fields = getSettersToCheck();
        if (!fields.isEmpty()) {
            errors.addAll(new SetterTestRunner(clazz, beanInfo, fields).runTests());
        }
        return errors;
    }

    /**
     * Run equals tests on class under test if applicable. Test will be run on
     * the equals method only if declared within the class under test.
     * Fields used to determine equality are those provided in the #includeInEquals method.
     * Included fields will be tested with all combinations of non-null and if applicable, null values.
     * Only those fields will be used and other fields may contain random data.
     * @return List of zero to many errors found during the run.
     */
    public List<String> runEqualsTests() {
        final List<String> errors = new ArrayList<>();
        if (beanMethodsMap.containsKey("equals")) {
            errors.addAll(new EqualsTestRunner<T>(clazz, beanInfo, includeInEquals).runTests());
        }
        return errors;
    }

    /**
     * Run hashCode tests on class under test if applicable. Test will be run on
     * the hashCode method only if declared within the class under test.
     * Fields used to determine equality are those provided in the #includeInEquals method.
     * Included fields will be tested with all combinations of non-null and if applicable, null values.
     * Only those fields will be used and other fields may contain random data.
     * @return List of zero to many errors found during the run.
     */
    public List<String> runHashTests() {
        final List<String> errors = new ArrayList<>();
        if (beanMethodsMap.containsKey("hashCode")) {
            errors.addAll(new HashcodeTestRunner<T>(clazz, beanInfo, includeInEquals).runTests());
        }
        return errors;
    }

    /**
     * Run a simple toString test with default values in all properties.  Validates that toString does not throw a
     * Null Pointer Exception.
     * @return List of zero to many errors found during the run.
     */
    public List<String> runToStringTests() {
        final List<String> errors = new ArrayList<>();
        if (beanMethodsMap.containsKey("toString")) {
            errors.addAll(new ToStringTestRunner<T>(clazz, beanInfo).runTests());
        }
        return errors;
    }


    /**
     * Return the set of properties that have been selected for getter method validation.
     * This set includes all properties for the class under test that have not
     * already been excluded at the property level.  This set is then filtered as follows:
     * <ul>
     * <li>Intersection of include setters set if specified</li>
     * <li>Remaining fields minus excluded setters set</li>
     * </ul>
     *
     * @return Set of property descriptors that may be empty.
     */
    protected Set<PropertyDescriptor> getGettersToCheck() {
        return filterMethods(includeGetters, excludeGetters);
    }

    /**
     * Return the set of properties that have been selected for setter method validation.
     * This set includes all properties for the class under test that have not
     * already been excluded at the property level.  This set is then filtered as follows:
     * <ul>
     * <li>Intersection of include setters set if specified</li>
     * <li>Remaining fields minus excluded setters set</li>
     * </ul>
     *
     * @return Set of property descriptors that may be empty.
     */
    protected Set<PropertyDescriptor> getSettersToCheck() {
        return filterMethods(includeSetters, excludeSetters);
    }

    /**
     * Return the set of properties that have been selected for method validation.
     * This set includes all properties for the class under test that have not
     * already been excluded at the property level.  This set is then filtered as follows:
     * <ul>
     * <li>Intersection of included methods set if specified</li>
     * <li>Remaining fields minus excluded methods set</li>
     * </ul>
     *
     * @param include set of property names to include when selecting methods
     * @param exclude set of property names to exclude when selecting methods
     * @return Set of property descriptors that may be empty.
     */
    protected Set<PropertyDescriptor> filterMethods(Set<String> include, Set<String> exclude) {
        if (include.isEmpty()) {
            return getFieldsToCheck().stream()
                    .filter(field -> !exclude.contains(field.getName()))
                    .collect(Collectors.toSet());
        } else {
            return getFieldsToCheck().stream()
                    .filter(field -> include.contains(field.getName()))
                    .filter(field -> !exclude.contains(field.getName()))
                    .collect(Collectors.toSet());
        }
    }

    /**
     * Return the set of properties that have been selected for validation.
     * This set includes all properties for the class under test, which is then filtered as follows:
     * <ul>
     * <li>Intersection of include fields set if specified</li>
     * <li>Remaining fields minus excluded fields set</li>
     * </ul>
     *
     * @return Set of property descriptors that may be empty.
     */
    protected Set<PropertyDescriptor> getFieldsToCheck() {
        return getFieldsToCheck(beanInfo, includeFields, excludeFields);
    }

    /**
     * Return the set of properties that have been selected for validation.
     * This set includes all properties for the class under test, which is then filtered as follows:
     * <ul>
     * <li>Intersection of include fields set if specified</li>
     * <li>Remaining fields minus excluded fields set</li>
     * </ul>
     *
     * @param beanInfo information regarding properties and methods for the class under test.
     * @param fieldsToInclude set of property names to include when selecting fields
     * @param fieldsToExclude set of property names to exclude when selecting fields
     * @return Set of property descriptors that may be empty.
     */
    public static  Set<PropertyDescriptor> getFieldsToCheck(BeanInfo beanInfo, Set<String> fieldsToInclude,
                                                            Set<String> fieldsToExclude) {
        if (fieldsToInclude.isEmpty()) {
            return Arrays.stream(beanInfo.getPropertyDescriptors())
                    .filter(field -> !fieldsToExclude.contains(field.getName()))
                    .collect(Collectors.toSet());
        } else {
            return Arrays.stream(beanInfo.getPropertyDescriptors())
                    .filter(field -> fieldsToInclude.contains(field.getName()))
                    .filter(field -> !fieldsToExclude.contains(field.getName()))
                    .collect(Collectors.toSet());
        }
    }
}
