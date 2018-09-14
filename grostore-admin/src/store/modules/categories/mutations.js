import Types from "./types/mutation";

export default {
  [Types.CHANGE_SELECTED_PATH](state, payload) {
    let cnt = 0, index;

    state.selectedPath = state.selectedPath.filter(c => {
      if(c.child && c.child.indexOf(payload) !== -1) {
        index = cnt;
      }
      if(cnt > index) {
        return false;
      }
      cnt++;
      return true;
    });
    if(payload.child && payload.child.length > 0) {
      state.selectedPath.push(payload);
    }
  },
}
