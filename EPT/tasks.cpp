#include <iostream>
using namespace std;

void v_alloc_table_fill_34(int iSize) {
    if (iSize <= 0) {
        cout << "Size of array must be greater than 0" << endl;
        return;
    }

    int myMagicNumber = 34; //it's always a better idea to use variables instead of some random numbers
    int *table = new int[iSize];

    for (int i = 0; i < iSize; i++) {
        table[i] = myMagicNumber;
    }

    //so as written i will do it after but it could be done in upper loop
    for (int i = 0; i < iSize; i++) {
        cout << table[i] << " ";
    }
    cout << endl;

    delete[] table;
}

bool b_alloc_table_2_dim(int ***piTable, int iSizeX, int iSizeY) {
    if (iSizeX <= 0 || iSizeY <= 0) {
        cout << "Size of array must be greater than 0";
        return false;
    }

    *piTable = new int*[iSizeX];
    for (int i = 0; i < iSizeX; i ++) {
        (*piTable)[i] = new int[iSizeY];
        //debug
        for (int j = 0; j < iSizeY; j++) {
            (*piTable)[i][j] = 0;
        }
    }

    return true;
}

bool b_dealloc_table_2_dim(int ***piTable) {
    int lenX = sizeof(piTable)/sizeof(int); //this way size parameters are not needed

    for (int i = 0; i < lenX; i++) {
        delete (*piTable)[i];
    }
    delete[] *piTable;
    return true;
}