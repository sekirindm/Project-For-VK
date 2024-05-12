# Преимущества технологий
В этом проекте я использовал следующие технологии:

## Koin
При выборе механизма внедрения зависимостей я остановился на Koin вместо Dagger2 или Hilt по нескольким причинам:

Простота использования: Koin предлагает простой и интуитивно понятный синтаксис для определения зависимостей без необходимости в явных модулях.
Легковесность: Поскольку проект небольшой, я предпочел использовать легковесное решение, которое не требует сложной конфигурации и обеспечивает быструю разработку.

## Picasso
Для загрузки изображений в приложении я выбрал Picasso по следующим причинам:

Простота использования: Picasso предоставляет простой API для загрузки и отображения изображений без необходимости в сложной настройке.
Эффективность: Библиотека хорошо оптимизирована для загрузки и кэширования изображений, что обеспечивает быструю и эффективную работу приложения.


## ListAdapter для RecyclerView
Для отображения списков данных в приложении я использовал ListAdapter вместо обычного Adapter по следующим причинам:

Упрощенная реализация: ListAdapter предоставляет удобный способ реализации списков данных с поддержкой автоматического обновления при изменении данных.
Повышенная производительность: Благодаря встроенной поддержке DiffUtil, ListAdapter обеспечивает оптимальную производительность при обновлении элементов списка.

## Timber

Для ведения логов в приложении я использовал библиотеку Timber. Она предоставляет простой и удобный способ логирования событий в приложении. Преимущества Timber включают простоту использования, поддержку различных уровней логирования и гибкость настройки.

## Обнаружение сети

Я решил добавить в приложение функцию обнаружения сети. Если интернет-соединение отсутствует, список с продуктами становиться invisible, и высвечивается соответсвующий текст.
После повторного включения интернета (если его отключали), нужно свайпнуть экран вверх для прогрузки айтемов.


# Заметки

поле descriptions перенесен с главного экрана на экран детальной информации, для сохранения хорошего вида приложения.

# Как запустить проект
Клонируйте репозиторий на свой компьютер.
Откройте проект в выбранной IDE.
Соберите и запустите проект на устройстве или эмуляторе.
