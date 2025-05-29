package org.fs.comparator.processor.name;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.strategies.filter.FilterExcludeFields;
import org.fs.comparator.comparator.strategies.validator.ValidateDifferentTypeStrategy;
import org.fs.comparator.comparator.type.DifferentFieldsComparator;
import org.fs.comparator.comparator.type.ExcludeFieldsComparator;
import org.fs.comparator.comparator.type.OnlyFieldsComparator;
import org.fs.comparator.container.ConditionContainer;

public class ComparatorByName implements Terminable {
    private final ConditionContainer conditionContainer;

    public ComparatorByName(ConditionContainer conditionContainer) {
        this.conditionContainer = conditionContainer;
    }

    public ExcludeFieldsComparator excludeFields(String... fields) {
        return new ExcludeFieldsComparator(conditionContainer, fields);
    }

    public OnlyFieldsComparator onlyFields(String... fields) {
        return new OnlyFieldsComparator(conditionContainer, fields);
    }

    public DifferentFieldsComparator differentFieldsCompare(String... fields) {
        return new DifferentFieldsComparator(conditionContainer, fields);
    }

    public ComparatorByName compareDifferentTypes() {
        conditionContainer.addStrategy(new ValidateDifferentTypeStrategy());

        return this;
    }

    @Override
    public boolean compare() {
        return conditionContainer.compare();
    }
}
