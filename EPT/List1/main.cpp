#include "tasks.h"
#include "CTable.h"

int main() {
    cout << "   Task 1" << endl;
    v_alloc_table_fill_34(4);

    cout << endl << "   Task 2" << endl;
    int **piTable;
    int iSizeX = 3;
    int iSizeY = 5;
    b_alloc_table_2_dim(&piTable, iSizeX, iSizeY);

    for (int i = 0; i < iSizeX; i++) {
        for (int j = 0; j < iSizeY; j++) {
            cout << piTable[i][j] << " ";
        }
        cout << endl;
    }

    cout << endl << "   Task 3" << endl;
    b_dealloc_table_2_dim(&piTable);
    cout << "memory freed" << endl;

    cout << endl << "   Task 4" << endl;

    int iTableLen = 5;

    //using all constructors
    CTable cDefaultTable = CTable();
    CTable cTable = CTable("firstOne", iTableLen);
    CTable cTableCopy = CTable(cTable);

    //creating cTable on heap
    CTable *pcTableNew = new CTable();
    pcTableNew->vSetName("RenamedTable");

    //changing size
    cTable.bSetNewSize(-1);

    iTableLen = 3;
    cTable.bSetNewSize(iTableLen);

    //cloning the table
    CTable *cTableClone = pcTableNew->pcClone();

    //modifications using procedures
    iTableLen = 4;
    v_mod_tab(cTable, iTableLen); //makes a copy

    v_mod_tab(&cTable, iTableLen); //edits original table

    delete cTableClone;
    delete pcTableNew;

    return 0;
}
