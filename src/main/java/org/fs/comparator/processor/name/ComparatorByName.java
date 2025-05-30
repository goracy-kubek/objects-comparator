package org.fs.comparator.processor.name;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.processors.extractor.ExtractByName;
import org.fs.comparator.comparator.processors.validator.ValidateDifferentType;
import org.fs.comparator.comparator.type.AddDifferentFieldsToCompare;
import org.fs.comparator.comparator.type.ExcludeFields;
import org.fs.comparator.comparator.type.OnlyFields;
import org.fs.comparator.container.ConditionProcessor;

public class ComparatorByName implements Terminable {
    private final ConditionProcessor conditionProcessor;

    public ComparatorByName(ConditionProcessor conditionProcessor) {
        conditionProcessor.addStrategy(new ExtractByName());

        this.conditionProcessor = conditionProcessor;
    }

    public ComparatorByName excludeFields(String... fields) {
        new ExcludeFields(conditionProcessor, fields);

        return this;
    }

    public OnlyFields onlyFields(String... fields) {
        return new OnlyFields(conditionProcessor, fields);
    }

    /**
     * Add different fields to compare. Added fields exclude all fields that will meet on the path.
     * @param fields Fields to compare
     * @return Comparator
     */
    public AddDifferentFieldsToCompare addDifferentFieldsToCompare(String... fields) {
        return new AddDifferentFieldsToCompare(conditionProcessor, fields);
    }

    public ComparatorByName compareDifferentTypes() {
        conditionProcessor.addStrategy(new ValidateDifferentType());

        return this;
    }

    @Override
    public boolean compare() {
        return conditionProcessor.compare();
    }
}
