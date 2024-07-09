class HelperFunctions {
  static Map<String, String> stringToMap(String javaMapString) {
    print('******+++++++++++++++++++++++++++++++++++++++++$javaMapString');
    String cleanedString = javaMapString.substring(1, javaMapString.length - 1);

    List<String> keyValuePairs = cleanedString.split(', ');

    Map<String, String> resultMap = {};

    for (String pair in keyValuePairs) {
      List<String> keyValue = pair.split('=');
      if (keyValue.length == 2) {
        String key = keyValue[0];
        String value = keyValue[1];
        resultMap[key] = value;
      }
    }
    print('******+++++++++--------------------------${resultMap.toString()}');
    return resultMap;
  }

  static Map<String, String> linkStringToMap(String javaMapString) {
    print('******+++++++++++++++++++++++++++++++++++++++++$javaMapString');

    // Remove the curly braces at the beginning and the end
    String cleanedString = javaMapString.substring(1, javaMapString.length - 1);

    Map<String, String> resultMap = {};
    int start = 0;
    while (start < cleanedString.length) {
      // Find the key
      int indexOfEquals = cleanedString.indexOf('=', start);
      if (indexOfEquals == -1) break;

      String key = cleanedString.substring(start, indexOfEquals).trim();

      // Find the value
      int indexOfComma = cleanedString.indexOf(', ', indexOfEquals);
      String value;
      if (indexOfComma == -1) {
        value = cleanedString.substring(indexOfEquals + 1).trim();
        start = cleanedString.length; // End the loop
      } else {
        value = cleanedString.substring(indexOfEquals + 1, indexOfComma).trim();
        start = indexOfComma + 2;
      }

      resultMap[key] = value;
    }

    print('******+++++++++--------------------------${resultMap.toString()}');
    return resultMap;
  }

  static List<String> stringToList(String javaArrayListString) {
    String cleanedString =
        javaArrayListString.substring(1, javaArrayListString.length - 1);

    List<String> items = cleanedString.split(', ');

    List<String> trimmedItems = items.map((item) => item.trim()).toList();

    return trimmedItems;
  }

  static List<List<String>> stringToListOfList(String javaArrayListString) {
    String cleanedString =
        javaArrayListString.substring(1, javaArrayListString.length - 1);

    List<String> listComponents = cleanedString.split('], [');

    List<List<String>> result = listComponents.map((component) {
      String cleanedComponent =
          component.replaceAll('[', '').replaceAll(']', '');
      List<String> items =
          cleanedComponent.split(',').map((item) => item.trim()).toList();
      return items;
    }).toList();

    return result;
  }

  static String padLeftWithZero(String input) {
    if (input.length == 1) {
      return input.padLeft(2, '0');
    }
    return input;
  }

  static String getFormattedDate() {
    DateTime now = DateTime.now();
    String year = now.year.toString();
    String month =
        now.month.toString().padLeft(2, '0'); // افزودن صفر در صورت نیاز
    String day = now.day.toString().padLeft(2, '0'); // افزودن صفر در صورت نیاز
    return '$year/$month/$day';
  }

  static String formatDate(String day, String month, String year) {
    return '20$year/$month/$day';
  }

  static double calculateSizeOfSizedBox(
      int nuberOfContainer, double sizeOfEachContainer) {
    return nuberOfContainer * sizeOfEachContainer;
  }
}
