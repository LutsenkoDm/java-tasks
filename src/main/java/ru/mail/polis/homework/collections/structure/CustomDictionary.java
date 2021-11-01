package ru.mail.polis.homework.collections.structure;

import java.util.*;

/**
 * Задание оценивается в 4 балла.
 * Необходимо реализовать класс который умеет хранить строки и возвращать
 * список строк состоящий из того же набора буков, что ему передали строку.
 * Напишите какая сложность операций у вас получилась для каждого метода.
 */
public class CustomDictionary {

    private final Map<Map<Character, Integer>, Set<String>> dictionary = new HashMap<>();
    private int size;

    /**
     * Сохранить строку в структуру данных
     *
     * @param value - передаваемая строка
     * @return - успешно сохранили строку или нет.
     * <p>
     * Сложность - O(k)
     */
    public boolean add(String value) {
        if (value == null || value.equals("")) {
            throw new IllegalArgumentException();
        }
        boolean isAdd = false;
        Map<Character, Integer> countCharsMap = createCountCharsMap(value);
        if (dictionary.containsKey(countCharsMap)) {
            isAdd = dictionary.get(countCharsMap).add(value);
        } else {
            Set<String> words = new HashSet<>();
            if (words.add(value)) {
                isAdd = dictionary.put(countCharsMap, words) == null;
            }
        }
        if (isAdd) {
            size++;
        };
        return isAdd;
    }

    /**
     * Проверяем, хранится ли такая строка уже у нас
     *
     * @param value - передаваемая строка
     * @return - есть такая строка или нет в нашей структуре
     * <p>
     * Сложность - O(k)
     */
    public boolean contains(String value) {
        Map<Character, Integer> countCharsMap = createCountCharsMap(value);
        if (!dictionary.containsKey(countCharsMap)) {
            return false;
        }
        return dictionary.get(countCharsMap).contains(value);
    }

    /**
     * Удаляем сохраненную строку если она есть
     *
     * @param value - какую строку мы хотим удалить
     * @return - true если удалили, false - если такой строки нет
     * <p>
     * Сложность - O(k)
     */
    public boolean remove(String value) {
        Map<Character, Integer> countCharsMap = createCountCharsMap(value);
        if (!dictionary.containsKey(countCharsMap)) {
            return false;
        }
        Set<String> charsWords = dictionary.get(countCharsMap);
        boolean isRemoved = charsWords.remove(value);
        if (charsWords.isEmpty()) {
            dictionary.remove(countCharsMap);
        }
        if (isRemoved) {
            size--;
        };
        return isRemoved;
    }

    /**
     * Возвращает список из сохраненных ранее строк, которые состоят
     * из того же набора букв что нам передали строку.
     * Примеры:
     * сохраняем строки ["aaa", "aBa", "baa", "aaB"]
     * При поиске по строке "AAb" нам должен вернуться следующий
     * список: ["aBa","baa","aaB"]
     * <p>
     * сохраняем строки ["aaa", "aAa", "a"]
     * поиск "aaaa"
     * результат: []
     * Как можно заметить - регистр строки не должен влиять на поиск, при этом
     * возвращаемые строки хранятся в том виде что нам передали изначально.
     *
     * @return - список слов которые состоят из тех же букв, что и передаваемая
     * строка.
     * <p>
     * Сложность - O(k)
     */
    public List<String> getSimilarWords(String value) {
        Map<Character, Integer> countCharsMap = createCountCharsMap(value);
        return dictionary.containsKey(countCharsMap) ? new LinkedList<>(dictionary.get(countCharsMap)) : Collections.emptyList();
    }

    /**
     * Колл-во хранимых строк.
     *
     * @return - Колл-во хранимых строк.
     * <p>
     * Сложность - O(1)
     */
    public int size() {
        return size;
    }

    private Map<Character, Integer> createCountCharsMap(String value) {
        Map<Character, Integer> countCharsMap = new HashMap<>();
        for (char c : value.toLowerCase(Locale.ROOT).toCharArray()) {
            if (countCharsMap.containsKey(c)) {
                countCharsMap.replace(c, countCharsMap.get(c) + 1);
            } else {
                countCharsMap.put(c, 1);
            }
        }
        return countCharsMap;
    }
}
