class HelperFunctions {
  static Map<String, String> stringToMap(String javaMapString) {
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
    String cleanedString = javaArrayListString.substring(1, javaArrayListString.length - 1);

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

}
