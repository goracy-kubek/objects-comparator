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

    public ExcludeFields excludeFields(String... fields) {
        return new ExcludeFields(conditionProcessor, fields);
    }

    public OnlyFields onlyFields(String... fields) {
        return new OnlyFields(conditionProcessor, fields);
    }

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
