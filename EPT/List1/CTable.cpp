#include "CTable.h"
using namespace std;


CTable::CTable() {
    s_name = "default_name";
    i_table_len = 10; //default value
    cout << "nonp: " << s_name << endl;
    i_table = new int[i_table_len];
}

CTable::CTable(string sName, int iTableLen) {
    if (iTableLen <= 0) {
        cout << "Wrong argument: Table size must be greater than 0 - setting default value" << endl;
        i_table_len = 10; //default value
    }
    else {
        i_table_len = iTableLen;
    }
    s_name = sName;
    cout << "parameter: " << s_name << endl;
    i_table = new int[i_table_len];
}

CTable::CTable(const CTable &pcOther) {
    s_name = pcOther.s_name + "_copy";
    i_table_len = pcOther.i_table_len;
    i_table = new int[i_table_len];
    for (int i = 0; i < i_table_len; i++) {
        i_table[i] = pcOther.i_table[i];
    }
    cout << "copy: " << s_name << endl;
}

CTable::~CTable() {
    cout << "deleting " << s_name << endl;
}

void CTable::vSetName(string sName) {
    s_name = sName;
}

bool CTable::bSetNewSize(int iTableLen) {
    if (iTableLen <= 0) {
        cout << "Wrong argument: Table size must be greater than 0" << endl;
        return false;
    }

    int *newTable = new int[iTableLen];
    for (int i = 0; i < min(iTableLen, i_table_len); i++) {
        newTable[i] = i_table[i];
    }

    i_table_len = iTableLen;
    delete[] i_table;
    i_table = newTable;
    return true;
}

CTable *CTable::pcClone() {
    CTable *cCopy = new CTable(*this); //in this way we can reuse the code
    return cCopy;
}


void v_mod_tab(CTable *pcTab, int iNewSize) {
    //this one will change the pcTab
    pcTab->bSetNewSize(iNewSize);
}

void v_mod_tab(CTable cTab, int iNewSize) {
    //this one creates a copy
    cTab.bSetNewSize(iNewSize);
}
