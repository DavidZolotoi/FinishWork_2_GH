from datetime import datetime
import os

def GetFilePath(fileName):
    """Определяет местоположение исполняемого файла и его родителя.
    Определяет используемый разделитель в строке пути.
    Возвращает строку - созданный путь для будущего файла справочника.
    """
    filePath = os.path.abspath(__file__)
    fileParent = (os.path.dirname(os.path.normpath(filePath)))
    sep = "/" if ("/" in filePath) else "\\"
    return fileParent + sep + fileName

def CreateFile(filePath):
    """Создание файла, если он еще не существует"""
    if (not os.path.exists(filePath)):
        with open(filePath, "w+", encoding="utf-8") as myFile:
            myFile.write("")

def GetMyList(filePath):
    """Вернуть блокнот в виде листа"""
    pass

def ShowNotes(filePath):
    """Пункт меню. Показать весь справочник"""
    pass

def AddNote(filePath):
    """Пункт меню. Добавить контакт"""
    pass

def FindNote(filePath):
    """Пункт меню. Найти контакт"""
    pass

def DeleteNote(filePath):
    """Пункт меню. Удалить контакт"""
    pass

def EditNote(filePath):
    """Пункт меню. Редактировать контакт"""
    pass

def ExportNotes(filePath):
    """Пункт меню. Экспортировать контакты"""
    pass

def ImportNotes(filePath):
    """Пункт меню. Импортировать контакты"""
    pass

def ExitProgram(filePath):
    """Пункт меню. Завершить программу через стандартный механизм выброса исключения"""
    raise SystemExit

def GetMenuDictionary():
    """Создать словарь для пунктов меню.
    Ключ - порядковый номер.
    Значение - кортеж из ссылки на функцию и текстового описания.
    """
    menuDict = {
        1: (ShowNotes,   "Показать блокнот"),   # добавить фильтр даты
        2: (AddNote,     "Добавить заметку"),
        3: (FindNote,    "Найти заметку"),
        4: (DeleteNote,  "Удалить заметку"),
        5: (EditNote,    "Редактировать заметку"),
        6: (ExportNotes, "Экспортировать заметки"),
        7: (ImportNotes, "Импортировать заметки"),
        8: (ExitProgram, "Выйти из программы")
    }
    return menuDict

def GetActionFromMenu(menuDict, filePath):
    """Показ меню и выбор действия"""
    print("Меню:")
    for key in menuDict:
        print(f"{key}. {menuDict[key][1]}.")
    actionFromMenu = int(input("Выберите действие: "))
    return actionFromMenu

def main() -> None:
    """Точка входа"""
    fileName = "Notebook.csv"  # имя файла справочника заметок (блокнота)
    filePath = GetFilePath(fileName)            # формирование пути для справочника (рядом с Note.py)
    CreateFile(filePath)                        # на всякий случай во избежание проблем - необязательно
    menuDict = GetMenuDictionary()              # создание меню
    report = ""                                 # отчет о проделанной работе по выбранному действию
    while (True):                               # бескоченый цикл выбора пункта меню (завершается спец.пунктом)
        actionFromMenu = GetActionFromMenu(menuDict, filePath)
        # Каждый метод возвращает report и tmp
        # report - отчет для вывода на консоль - для пользователя
        # tmp - какой-то объект для технических целей - в зависимости от метода - для программиста
        report, tmp = menuDict[actionFromMenu][0](filePath)
        os.system('cls||clear')
        print(report)

if __name__ == '__main__':
    main()
