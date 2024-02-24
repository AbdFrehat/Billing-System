import { createSlice } from "@reduxjs/toolkit";

const uiSlice = createSlice({
  name: "ui",
  initialState: { maxSideBar: false },
  reducers: {
    toggle(state) {
      state.maxSideBar = !state.maxSideBar;
    },
  },
});

export const uiActions = uiSlice.actions;
export default uiSlice;
