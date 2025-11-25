If objects share fields with the same names, you might need to compare them based only on those fields.

It could be "historical problems" with no option for refactoring.
Mapstruct mapping them to each other can cause problems with other fields and future extensions
Creating a third object with fields you need can't update by itself.
If objects gain more fields that need to be compared, you have to update the third object manually.

The only solution is to compare objects by their field names automatically.

This library allows you to do exactly that.

You can choose options, specify or exclude certain fields, and even use fields from nested classes.
