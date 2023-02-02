# Programming
# [Лабораторная работа #1](https://github.com/LizaGribich/Programming/tree/main/Calculations(lab1))
Написать программу на языке Java, выполняющую соответствующие варианту действия. Программа должна соответствовать следующим требованиям:

Она должна быть упакована в исполняемый jar-архив.
Выражение должно вычисляться в соответствии с правилами вычисления математических выражений (должен соблюдаться порядок выполнения действий и т.д.).
Программа должна использовать математические функции из стандартной библиотеки Java.
Результат вычисления выражения должен быть выведен в стандартный поток вывода в заданном формате.
Выполнение программы необходимо продемонстрировать на сервере helios.

Создать одномерный массив a типа short. Заполнить его чётными числами от 2 до 24 включительно в порядке возрастания.
Создать одномерный массив x типа float. Заполнить его 10-ю случайными числами в диапазоне от -2.0 до 2.0.
Создать двумерный массив a размером 12x10. Вычислить его элементы по следующей формуле (где x = x[j]):
- если a[i] = 16, то a[i][j]=sin(cos((x+14x)x));
- если a[i] ∈ {6, 8, 18, 20, 22, 24}, то a[i][j]=arcsin(sin((ln(|x|)−1)sin(x)));
- для остальных значений a[i]: a[i][j]=(2e(2⋅cos(x))2)cos((cos(x))ex2).
Напечатать полученный в результате массив в формате с двумя знаками после запятой.

# [Лабораторная работа #2](https://github.com/LizaGribich/Programming/tree/main/Pokemons(lab2))
На основе базового класса Pokemon написать свои классы для заданных видов покемонов. Каждый вид покемона должен иметь один или два типа и стандартные базовые характеристики:

очки здоровья (HP)
атака (attack)
защита (defense)
специальная атака (special attack)
специальная защита (special defense)
скорость (speed)
Классы покемонов должны наследоваться в соответствии с цепочкой эволюции покемонов. На основе базовых классов PhysicalMove, SpecialMove и StatusMove реализовать свои классы для заданных видов атак.

Атака должна иметь стандартные тип, силу (power) и точность (accuracy). Должны быть реализованы стандартные эффекты атаки. Назначить каждому виду покемонов атаки в соответствии с вариантом. Уровень покемона выбирается минимально необходимым для всех реализованных атак.

Используя класс симуляции боя Battle, создать 2 команды покемонов (каждый покемон должен иметь имя) и запустить бой.

Базовые классы и симулятор сражения находятся в jar-архиве (обновлен 9.10.2018, исправлен баг с добавлением атак и кодировкой). Документация в формате javadoc - здесь.

Информацию о покемонах, цепочках эволюции и атаках можно найти на сайтах http://poke-universe.ru, http://pokemondb.net, http://veekun.com/dex/pokemon

![](https://sun9-13.userapi.com/impg/lKzHzHcXvV4rONIJpdCkXzigIZ4GLSrJL1mLHQ/uNnh1zkVBUo.jpg?size=1391x459&quality=96&sign=e026e64c68eaabbfd5424824202e02c9&type=album)

# [Лабораторная работа #3](https://github.com/LizaGribich/Programming/tree/main/Moomin_Troll(lab3-4))
Описание предметной области, по которой должна быть построена объектная модель:

А на дворе под дождем стоял Муми-тролль и удивленно озирал высокий зеленый холм, на котором прямо на глазах распускались цветы и созревали плоды, меняя цвет из зеленого в желтый, из желтого в красный. Снусмумрик выступил вперед и с интересом стал осматривать холм. Ни окон, ни дверей. Сплошной ковер дикой растительности. Снусмумрик ухватился за какой-то стебель и потянул. Стебель был упругий, словно резиновый, и не выдергивался из земли! Как бы невзначай обвился он вокруг шляпы Снусмумрика и снял ее.
Программа должна удовлетворять следующим требованиям:

Доработанная модель должна соответствовать принципам SOLID.
Программа должна содержать как минимум два интерфейса и один абстрактный класс (номенклатура должна быть согласована с преподавателем).
В разработанных классах должны быть переопределены методы equals(), toString() и hashCode().
Программа должна содержать как минимум один перечисляемый тип (enum).
Порядок выполнения работы:

Доработать объектную модель приложения.
Перерисовать диаграмму классов в соответствии с внесёнными в модель изменениями.
Согласовать с преподавателем изменения, внесённые в модель.
Модифицировать программу в соответствии с внесёнными в модель изменениями.

# [Лабораторная работа #4](https://github.com/LizaGribich/Programming/tree/main/Moomin_Troll(lab3-4))
Описание предметной области, по которой должна быть построена объектная модель:

Тут из зарослей папоротников, проросших в ванной, вынырнул Ондатр и жалобным голосом сказал: Лианы проросли сквозь печную трубу, оплели крышу и окутали весь Муми-дом пышным зеленым ковром. А на дворе под дождем стоял Муми-тролль и удивленно озирал высокий зеленый холм, на котором прямо на глазах распускались цветы и созревали плоды, меняя цвет из зеленого в желтый, из желтого в красный. Снусмумрик выступил вперед и с интересом стал осматривать холм. Ни окон, ни дверей. Сплошной ковер дикой растительности. Снусмумрик ухватился за какой-то стебель и потянул. Стебель был упругий, словно резиновый, и не выдергивался из земли! Как бы невзначай обвился он вокруг шляпы Снусмумрика и снял ее. Тем временем Снифф обежал вокруг наглухо заросшей веранды. Муми-тролль стремглав подлетел к отдушине и заглянул в нее.
Программа должна удовлетворять следующим требованиям:

В программе должны быть реализованы 2 собственных класса исключений (checked и unchecked), а также обработка исключений этих классов.
В программу необходимо добавить использование локальных, анонимных и вложенных классов (static и non-static).
Порядок выполнения работы:

Доработать объектную модель приложения.
Перерисовать диаграмму классов в соответствии с внесёнными в модель изменениями.
Согласовать с преподавателем изменения, внесённые в модель.
Модифицировать программу в соответствии с внесёнными в модель изменениями.
