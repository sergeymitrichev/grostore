export interface ICategory {
  id: number,
  name: string,
  parent: ICategory,
  child: ICategory[]
}
